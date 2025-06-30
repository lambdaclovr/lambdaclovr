package VideoStreamProcessor;

import Global.VideoEventData;
import Util.PropertyFileReader;
import org.apache.log4j.Logger;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsWithStateFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.GroupState;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Iterator;
import java.util.Properties;

public class VideoStreamProcessor {
    private static final Logger logger = Logger.getLogger(VideoStreamProcessor.class);

    public static void main(String[] args) throws Exception {
        Properties prop = PropertyFileReader.readPropertyFile("stream-processor.properties");

        SparkSession spark = SparkSession
                .builder()
                .appName("VideoStreamProcessor")
                .master(prop.getProperty("spark.master.url"))
                .getOrCreate();

        final String processedImageDir = prop.getProperty("processed.output.dir");
        logger.warn("Output directory for saving processed images is set to "+processedImageDir+". This is configured in processed.output.dir key of property file.");

        StructType schema =  DataTypes.createStructType(new StructField[] {
                DataTypes.createStructField("cameraId", DataTypes.StringType, true),
                DataTypes.createStructField("timestamp", DataTypes.TimestampType, true),
                DataTypes.createStructField("width", DataTypes.IntegerType, true),
                DataTypes.createStructField("height", DataTypes.IntegerType, true),
                DataTypes.createStructField("type", DataTypes.StringType, true),
                DataTypes.createStructField("data", DataTypes.StringType, true)
        });


        Dataset<VideoEventData> ds = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", prop.getProperty("kafka.bootstrap.servers"))
                .option("subscribe", prop.getProperty("kafka.topic"))
                .option("kafka.max.partition.fetch.bytes", prop.getProperty("kafka.max.partition.fetch.bytes"))
                .option("kafka.max.poll.records", prop.getProperty("kafka.max.poll.records"))
                .load()
                .selectExpr("CAST(value AS STRING) as message")
                .select(functions.from_json(functions.col("message"), schema).as("json"))
                .select("json.*")
                .as(Encoders.bean(VideoEventData.class));

        KeyValueGroupedDataset<String, VideoEventData> kvDataset = ds.groupByKey(new MapFunction<VideoEventData, String>() {
            @Override
            public String call(VideoEventData value) throws Exception {
                return value.getCameraId();
            }
        }, Encoders.STRING());

        Dataset<VideoEventData> processedDataset = kvDataset.mapGroupsWithState(new MapGroupsWithStateFunction<String, VideoEventData, VideoEventData,VideoEventData>(){
            @Override
            public VideoEventData call(String key, Iterator<VideoEventData> values, GroupState<VideoEventData> state) throws Exception {
                logger.warn("CameraId=" + key + " PartitionId=" + TaskContext.getPartitionId());
                VideoEventData existing = null;
        
                if (state.exists()) {
                    existing = state.get();
                }
        
                VideoEventData processed = VideoMotionDetector.detectMotion(key, values, processedImageDir, existing);

                if(processed != null){
                    state.update(processed);
                }
                return processed;
            }}, Encoders.bean(VideoEventData.class), Encoders.bean(VideoEventData.class));
        
        StreamingQuery query = processedDataset.writeStream()
                .outputMode("update")
                .format("console")
                .start();

        query.awaitTermination();
    }
}
