package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer.lvsm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lambdaclovr.dsl.dao.AnomalyDAO;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.model.Anomaly;

public class LVSMConsumerToHbaseAnomaly {
	static Anomaly anomaly = null;
	static BufferedImage bufferedImage = null;

	public static void main(String[] args) throws InterruptedException {
		ObjectMapper mapper = new ObjectMapper();

		Properties props = new Properties();
		props.put("bootstrap.servers", GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES);
		props.put("group.id", "group");
		props.put("auto.offset.reset", "earliest");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		String topicName = GlobalClusterConfigurations.ALERTS_TOPIC;
		consumer.subscribe(Arrays.asList(topicName));

		while (true) {
			
			ConsumerRecords<String, String> records = consumer.poll(500);

			records.forEach(record -> {
				
				try {
					//Value Mapping from Json Message to NotificationData Object 
					anomaly = mapper.readValue(record.value(), Anomaly.class);

					System.out.println("Key: " + record.key());
				
					//Save to Anomaly Hbase table
					AnomalyDAO.create(anomaly);
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Cant parse the Received Notification Object");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
