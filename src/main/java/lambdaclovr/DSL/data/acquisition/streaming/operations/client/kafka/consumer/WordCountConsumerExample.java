package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

public class WordCountConsumerExample {
	private static final Pattern SPACE = Pattern.compile(" ");

	public static void main(String[] args) {

		SparkConf sparkConf = new SparkConf().setAppName("JavaDirectKafkaWordCount").setMaster("local[*]");
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "host:port");
		kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaParams.put("enable.auto.commit", false);
		kafkaParams.put("group.id", "spark-streaming-notes");
		
		Collection<String> topics = Arrays.asList("vidcam");

		// Create direct kafka stream with brokers and topics
		JavaInputDStream<ConsumerRecord<Object, Object>> messages = KafkaUtils.createDirectStream(jssc,
				LocationStrategies.PreferConsistent(), ConsumerStrategies.Subscribe(topics, kafkaParams));

		JavaDStream<Object> lines = messages.map(ConsumerRecord::value);
		 JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split((CharSequence) x)).iterator());
		    JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
		        .reduceByKey((i1, i2) -> i1 + i2);
		    wordCounts.print();
		
		
		// Start the computation
		jssc.start();
		try {
			jssc.awaitTermination();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}