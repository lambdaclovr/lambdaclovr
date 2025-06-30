package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class ConsumerCreator {

	public static KafkaConsumer<String, String> createConsumer() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConsumerConstants.BOOTSTRAP_SERVERS_CONFIG);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConsumerConstants.GROUP_ID_CONFIG);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IKafkaConsumerConstants.KEY_DESERIALIZER_CLASS_CONFIG);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IKafkaConsumerConstants.VALUE_DESERIALIZER_CLASS_CONFIG);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConsumerConstants.MAX_POLL_RECORDS_CONFIG);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, IKafkaConsumerConstants.ENABLE_AUTO_COMMIT_CONFIG);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConsumerConstants.AUTO_OFFSET_RESET_CONFIG);
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);		
		//consumer.subscribe(Collections.singletonList(topicName));
		return consumer;
	}

}
