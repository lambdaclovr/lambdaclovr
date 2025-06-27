package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

public class TopicSequentialConsumer {

	public static void main(String[] args) throws InterruptedException {

		Properties props = new Properties();
		props.put("bootstrap.servers", GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES);
		props.put("group.id", "group");
		props.put("auto.offset.reset", "earliest");
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("max.poll.records", 1);
		

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		String topicName = "cam";
		consumer.subscribe(Arrays.asList(topicName));
		System.out.println("Subscribed to topic " + topicName);
		int i = 0;
		while (true) {
			//Thread.sleep(200);
			System.out.println("Consuming...");
			ConsumerRecords<String, String> records = consumer.poll(500);
			for (ConsumerRecord<String, String> record : records)
				//System.out.println("part" + record.partition());
				System.out.printf("{" + "Topic = %s, Partition = %s, offset = %d, key = %s, value = %s\n ", record.topic(), record.partition(), record.offset(), record.key(), record.value() + "}");
		}
	}
}
