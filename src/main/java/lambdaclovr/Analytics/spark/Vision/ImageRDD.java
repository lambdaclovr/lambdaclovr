package org.spark.vision;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.nd4j.jita.conf.CudaEnvironment;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ImageRDD {
    private final static Logger logger = Logger.getLogger(ImageRDDTest.class);

    public static void main(String[] args) {
        CudaEnvironment.getInstance().getConfiguration().allowMultiGPU(true);

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        logger.debug("Log4j appender configuration is successful !!");

        String inputPath = "D:/siat/frames";

        SparkConf conf = new SparkConf()
                .setAppName("VidRDD")
                .setMaster("local[*]");

        JavaSparkContextExt sc = new JavaSparkContextExt(conf);


        System.out.println(">>> Loading CNN");
        SPFExtractor.getInstance().init();
        
        JavaRDD<Image> images = sc.imageFiles(inputPath);

        JavaRDD<INDArray> features = images.map(i -> SPFExtractor.getInstance().extractSpatialFeaturesUsingGPU(i.getMat())); // GPU
        features.foreach(f -> System.out.println(f));
    }
}
