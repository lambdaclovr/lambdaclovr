package lambdaclovr.dsl.data.acquisition.streaming;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

public class BasicProducerExample {

   public static void main(String[] args) throws InterruptedException{

       Properties props = new Properties();
       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES);
       //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:6667");
       //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:6667");
       //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "sandbox-hdp.hortonworks.com:9092");
       props.put(ProducerConfig.ACKS_CONFIG, "all");
       props.put(ProducerConfig.RETRIES_CONFIG, 0);
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
       props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
       //props.put(ProducerConfig.
       props.put("batch.size","16384"); // Maximum size of message 

       Producer<String, String> producer = new KafkaProducer<String, String>(props);
       TestCallback callback = new TestCallback();
       Random rnd = new Random();
       double n = 0;
       
       
       long start = System.currentTimeMillis();
	   long end = start + 1000; // 60 seconds * 1000 ms/sec
       
	   //for (long i = 0; i < 2000000000 ; i++) {
    	
	   while(true) {	   
    	   ProducerRecord<String, String> data = new ProducerRecord<String, String>("cam3", "Key" + n, "Message-***" + n);
    	   
    	   //Sent to Kafka Broker
           producer.send(data, callback);
           
           n++;
           System.out.println(n);
           if(System.currentTimeMillis() > end ) {
				
        	   System.out.println(n);
        	   System.exit(0);
			}
       }
   }
   
   
   public static class TestCallback implements Callback {
       @Override
       public void onCompletion(RecordMetadata recordMetadata, Exception e) {
           if (e != null) {
               System.out.println("Error while producing message to topic :" + recordMetadata);
               e.printStackTrace();
           } else {
               String message = String.format("sent message to topic:%s partition:%s  offset:%s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
               System.out.println(message);
           }
       }
   }
}
