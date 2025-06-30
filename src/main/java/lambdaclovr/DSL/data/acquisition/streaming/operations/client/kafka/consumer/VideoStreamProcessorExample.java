package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;

import java.util.Iterator;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsWithStateFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.KeyValueGroupedDataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.GroupState;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer.video.analytics.VideoMotionDetector;

public class VideoStreamProcessorExample {

	public static void main(String[] args) throws Exception {

		String topicName = "cam4";
		String processedImageDir = "F:\\cams\\output\\";
		// SparkSesion
		SparkSession spark = SparkSession
				.builder()
				.appName("VideoStreamProcessor")
				.master("local[2]")
				.config("spark.sql.streaming.checkpointLocation", "C:\\sparkApp\\checkpoints\\")
				.getOrCreate();

		// create schema for json message
		StructType schema = DataTypes.createStructType(
				new StructField[] { DataTypes.createStructField("cameraId", DataTypes.StringType, true),
						DataTypes.createStructField("timestamp", DataTypes.TimestampType, true),
						DataTypes.createStructField("rows", DataTypes.IntegerType, true),
						DataTypes.createStructField("cols", DataTypes.IntegerType, true),
						DataTypes.createStructField("type", DataTypes.IntegerType, true),
						DataTypes.createStructField("data", DataTypes.StringType, true) });

		// Create DataSet from stream messages from kafka
		Dataset<VideoEventData> ds = spark.readStream().format("kafka")
				.option("kafka.bootstrap.servers", GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES)
				.option("subscribe", topicName)
				.option("kafka.max.partition.fetch.bytes", IKafkaConsumerConstants.KAFKA_MAX_PARTITION_FETCH_BYTES) // 1097152
				.option("kafka.max.poll.records", IKafkaConsumerConstants.KAFKA_MAX_POLL_RECORDS) // 500
				.load().selectExpr("CAST(value AS STRING) as message")
				.select(functions.from_json(functions.col("message"), schema).as("json")).select("json.*")
				.as(Encoders.bean(VideoEventData.class));


		//key-value pair of cameraId-VideoEventData
		KeyValueGroupedDataset<String, VideoEventData> kvDataset = ds.groupByKey(new MapFunction<VideoEventData, String>() {
			@Override
			public String call(VideoEventData value) throws Exception {
				return value.getCameraId();
			}
		}, Encoders.STRING());
			
		//Process
		Dataset<VideoEventData> processedDataset = kvDataset.mapGroupsWithState(new MapGroupsWithStateFunction<String, VideoEventData, VideoEventData,VideoEventData>(){
			@Override
			public VideoEventData call(String key, Iterator<VideoEventData> values, GroupState<VideoEventData> state) throws Exception {
				VideoEventData existing = null;
				//check previous state
				if (state.exists()) {
					existing = state.get();
				}
				//detect motion
				VideoEventData processed = VideoMotionDetector.detectMotion(key,values,processedImageDir,existing);
				
				//update last processed
				if(processed != null){
					state.update(processed);
				}
				return processed;
			}}, Encoders.bean(VideoEventData.class), Encoders.bean(VideoEventData.class));

		
		//Start
		 StreamingQuery query = processedDataset
				 .writeStream()
			     .outputMode("update")
			     .format("console")
			     .start();

		// Await
		query.awaitTermination();
	}
}
