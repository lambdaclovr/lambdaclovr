package lambdaclovr.dsl.data.acquisition.streaming;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.kafka.clients.producer.ProducerConfig;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;

public class DeleteKafkaTopic {
	 final static boolean resetTopic = Boolean.parseBoolean(System.getenv("RESET_TOPIC"));
	 final static String topicName = "topic5";
	
	 
	 
	public static void main(String[] args) throws InterruptedException {

		String zookeeperConnect = "node3.khu:2181, node4.khu:2181";
		int sessionTimeoutMs = 10 * 1000;
		int connectionTimeoutMs = 8 * 1000;

		String topic = "topic6";
		int partitions = 1;
		int replication = 1;

		Properties topicConfig = new Properties();
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node3.khu:6667");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,	"org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put("batch.size", "16384");// maximum size of message
		
		ZkClient zkClient = new ZkClient( 
		        zookeeperConnect,
		        sessionTimeoutMs,
		        connectionTimeoutMs,
		        ZKStringSerializer$.MODULE$);
		
		//Security  for Kafka 
		boolean isSecureKafkaCluster = false;
		ZkUtils zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperConnect), isSecureKafkaCluster);
		
		System.out.println(topicName);
		
		if (AdminUtils.topicExists(zkUtils, topicName)) {
			 System.out.println("Deleting");
		      System.out.println("Deleting topic " + topicName);
		      AdminUtils.deleteTopic(zkUtils, topicName);

		      while (AdminUtils.topicExists(zkUtils, topicName)) {
		        System.out.println("Waiting for kafka to really delete topic ...");
		        TimeUnit.SECONDS.sleep(1);
		      }
		    }
	}
}
