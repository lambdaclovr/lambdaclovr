package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class TopicPartitionSequentialConsumer {
	
	public static void main(String[] args) throws IOException, InterruptedException{
	    Properties props = new Properties();
	    
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConsumerConstants.BOOTSTRAP_SERVERS_CONFIG);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConsumerConstants.GROUP_ID_CONFIG);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IKafkaConsumerConstants.KEY_DESERIALIZER_CLASS_CONFIG);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IKafkaConsumerConstants.VALUE_DESERIALIZER_CLASS_CONFIG);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConsumerConstants.MAX_POLL_RECORDS_CONFIG);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, IKafkaConsumerConstants.ENABLE_AUTO_COMMIT_CONFIG);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConsumerConstants.AUTO_OFFSET_RESET_CONFIG);
		

	    @SuppressWarnings("resource")
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	    
	    String topic = "cam";
	    TopicPartition topicPartition = new TopicPartition(topic, 3);
	    List<TopicPartition> topics = Arrays.asList(topicPartition);
	    consumer.assign(topics);
	    consumer.seekToEnd(topics);
	    long current = consumer.position(topicPartition);
	    consumer.seek(topicPartition, current);

	    while (true) {
	    	ConsumerRecords<String, String> records = consumer.poll(100);
	        for (ConsumerRecord<String, String> record : records) {
	            System.out.printf("partition = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
	        }
	        Thread.sleep(100);
	    }
	}

}
