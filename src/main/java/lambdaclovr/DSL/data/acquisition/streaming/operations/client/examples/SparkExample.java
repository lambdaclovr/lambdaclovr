package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkExample {
	public static void main(String args[]) {
        /* Define Spark Configuration */
        SparkConf conf = new SparkConf().setAppName("01-Getting-Started").setMaster("local[*]");
        
        /* Create Spark Context with configuration */
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        /* Create a Resilient Distributed Dataset for a log file 
         * Each line in log file become a record in RDD
         * */
        JavaRDD<String> lines = sc.textFile("C:\\Users\\Numan Khan\\Desktop\\f\\f.txt");
        
        System.out.println("--------------------------------------------------");
        System.out.println("Total lines in file " + lines.count());
        
        /* Map operation -> Mapping number of characters into each line as RDD */
        JavaRDD<Integer> lineCharacters = lines.map(s -> s.length());
        /* Reduce operation -> Calculating total characters */
        int totalCharacters = lineCharacters.reduce((a, b) -> a + b);
        
        System.out.println("Total characters in log file " + totalCharacters);
        
        /* Reduce operation -> checking each line for .html character pattern */
        System.out.println("Total URL with html extension in log file " 
                + lines.filter(oneLine -> oneLine.contains(".html")).count());
        
        /* Reduce operation -> checking each line for .gif character pattern */
        System.out.println("Total URL with gif extension in log file "
                + lines.filter(oneLine -> oneLine.contains(".gif")).count());
        
        
        System.out.println("----------------Write file----------------------------------");
        
        lines.saveAsTextFile("C:\\Users\\Numan Khan\\Desktop\\f\\g\\");
        sc.close();
    }
}