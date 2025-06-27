package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

public class ProducerCreator {
	
	
	public static Producer<String, String> createProducer() {	
	
		Properties properties = new Properties();
		
		//Configure Kafka Producer 
		properties.put(ProducerConfig.ACKS_CONFIG, IKafkaProducerConstants.ACKS_CONFIG);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, IKafkaProducerConstants.BATCH_SIZE_CONFIG); //20MB
		properties.put(ProducerConfig.RETRIES_CONFIG, IKafkaProducerConstants.RETRIES_CONFIG);		
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaProducerConstants.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, IKafkaProducerConstants.LINGER_MS_CONFIG);
		
		properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, IKafkaProducerConstants.MAX_REQUEST_SIZE_CONFIG);
		properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, IKafkaProducerConstants.COMPRESSION_TYPE_CONFIG);
		
		//String Serializer
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IKafkaProducerConstants.KEY_SERIALIZER_CLASS_CONFIG);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IKafkaProducerConstants.VALUE_SERIALIZER_CLASS_CONFIG);
		
		return new KafkaProducer<String, String>(properties);
	}
}
