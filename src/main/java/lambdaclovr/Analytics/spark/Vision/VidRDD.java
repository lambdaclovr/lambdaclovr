package org.spark.vision;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.nd4j.jita.conf.CudaEnvironment;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.IOException;

public class VidRDD {
    private final static Logger logger = Logger.getLogger(VidRDDTest.class);

    public static void main(String[] args) throws IOException {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        logger.debug("Log4j appender configuration is successful !!");

        String inputPath = "cam/or/dataset/path";
    
        SparkConf conf = new SparkConf()
                .setAppName("VidRDD")
                .setMaster("yarn")
                .set("spark.hadoop.fs.default.name", "host:port")
                .set("spark.hadoop.fs.defaultFS", "host:port")
                .set("spark.hadoop.fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName())
                .set("spark.hadoop.fs.hdfs.server", org.apache.hadoop.hdfs.server.namenode.NameNode.class.getName())
                .set("spark.hadoop.conf", org.apache.hadoop.hdfs.HdfsConfiguration.class.getName());

        JavaSparkContext sc = new JavaSparkContext(conf);

        SPFExtractor.getInstance().init();

        JavaRDD<Video> videos = sc.videoFiles(inputPath, false);

        JavaRDD<Frame> frames = videos.flatMap(v -> v.split(FrameOptions.KEYFRAMES, ImageOptions.FRAME));
        
        frames.map(f -> f.extractFeatures()).collect();
        
        JavaRDD<INDArray> features = frames.map(f -> SPFExtractor.getInstance().extractSpatialFeaturesUsingGPU(f.getMat()));
                
        sc.close();
    }

    public static String makeDouble(String str) {
        return str + ":" + str;
    }

    public static boolean isArray(Object obj)
    {
        return obj != null && obj.getClass().isArray();
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void printArray(String[] arr) {
        for (String e: arr) {
            System.out.println(e);
        }
    }
}
