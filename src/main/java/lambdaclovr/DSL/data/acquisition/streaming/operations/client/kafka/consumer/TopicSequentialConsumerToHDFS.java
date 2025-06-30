package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

public class TopicSequentialConsumerToHDFS {

	public static void main(String[] args) throws InterruptedException {

		Properties props = new Properties();
		props.put("bootstrap.servers", GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES);
		props.put("group.id", "--from-beginning");
		props.put("auto.offset.reset", "earliest");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("max.partition.fetch.bytes", "1097152");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		String topicName = "RVAS_1";
		consumer.subscribe(Arrays.asList(topicName));
		System.out.println("Subscribed to topic " + topicName);
		int i = 0;
		String key = "";
		String value = "";
		
		while (true) {
			//Thread.sleep(5000);
			System.out.println("Consuming...");
			ConsumerRecords<String, String> records = consumer.poll(500);
			System.out.println(records.count());
			for (ConsumerRecord<String, String> record : records)
			{	
				//System.out.println("part" + record.partition());
				//System.out.printf("{" + "Topic = %s, Partition = %s, offset = %d, key = %s, value = %s\n ", record.topic(), record.partition(), record.offset(), record.key(), record.value() + "}");
				key = record.key();
				value = record.value();
				
				//System.out.println(key + " & " + value);
				
				//Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonObject jsonObject = (new JsonParser()).parse(value).getAsJsonObject();
				
//				System.out.println(jsonObject.get("cameraId"));
//				System.out.println(jsonObject.get("timestamp"));
//				System.out.println(jsonObject.get("rows"));
//				System.out.println(jsonObject.get("cols"));
//				System.out.println(jsonObject.get("type"));
//				System.out.println(jsonObject.get("data"));
				
				//System.out.println("--------------------------");
			}
				
		}
	}
}
