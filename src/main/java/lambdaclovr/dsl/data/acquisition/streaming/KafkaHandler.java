package lambdaclovr.dsl.data.acquisition.streaming;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.common.KafkaException;
import kafka.server.ConfigType;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.TopicOperationException;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.data.acquisition.stream</h3>
 * <h3>Class Name: KafkaHandler</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide Kafka Topic realated operations .
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

public class KafkaHandler {
	 final static String topicName = "topic5";
	 final String zookeeperConnect = "node3.khu:2181, node4.khu:2181";
	 final int sessionTimeoutMs = 10 * 1000;
	 final int connectionTimeoutMs = 8 * 1000;
	 boolean isSecureKafkaCluster = false;
	 int partitions = 1;
	 int replication = 1;

	
	 /**
		* @param KafkaHandlerParm Object
		* @return boolean
		* @throws Exception
	 */
	 public KafkaHandlerParms createTopic(KafkaHandlerParms kafkaHandlerParms)
	{
		Properties properties = new Properties();	
		ZkClient zkClient = new ZkClient(
				kafkaHandlerParms.zookeeperServers, 
				kafkaHandlerParms.sessionTimeoutMs,
				kafkaHandlerParms.connectionTimeoutMs, 
				ZKStringSerializer$.MODULE$);
		 
		ZkUtils zkUtils = new ZkUtils(
				zkClient, 
				new ZkConnection(kafkaHandlerParms.zookeeperServers), 
				kafkaHandlerParms.isSecureKafkaCluster);	
		try {
				AdminUtils.createTopic(
			    		zkUtils,
			    		kafkaHandlerParms.topicName, 
			    		kafkaHandlerParms.partitions, 
			    		kafkaHandlerParms.replicationFactor, 
			    		properties,
			    		RackAwareMode.Enforced$.MODULE$);
				
		} catch (IllegalArgumentException | KafkaException e) {
	        throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
	    }
		zkClient.close();
		
		return kafkaHandlerParms;
	}


	 ///////////////////////////////////////
	
	 /**
		* @param KafkaHandlerParm Object
		* @return List 
		* @throws Exception
	 */
	 public void listTopic()
	{
		Map<String, List<PartitionInfo> > topicPartitonInfoMap;

		Properties props = new Properties();
		props.put("bootstrap.servers", GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES);
		props.put("group.id", "test-consumer-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		topicPartitonInfoMap = consumer.listTopics();		
		consumer.close();

		for (Map.Entry<String, List<PartitionInfo>> topic : topicPartitonInfoMap.entrySet()) {
	         System.out.println("Topic: "+ topic.getKey());
	         System.out.println("Value: " + topic.getValue() + "\n");
	      }
	}
	
	
	///////////////////////////////////////
	
	 /**
		* @param KafkaHandlerParm Object
		* @return boolean
		* @throws Exception
	 */
	 public void deleteTopic(KafkaHandlerParms kafkaHandlerParms)
	{
		ZkClient zkClient = new ZkClient(
				kafkaHandlerParms.zookeeperServers, 
				kafkaHandlerParms.sessionTimeoutMs, 
				kafkaHandlerParms.connectionTimeoutMs, 
				ZKStringSerializer$.MODULE$);	
		
		ZkUtils zkUtils = new ZkUtils(
				zkClient, 
				new ZkConnection(kafkaHandlerParms.zookeeperServers), 
				kafkaHandlerParms.isSecureKafkaCluster);
		
		if (AdminUtils.topicExists(zkUtils, topicName)) {
			 System.out.println("Deleting");
		      System.out.println("Deleting topic " + topicName);
		      AdminUtils.deleteTopic(zkUtils, topicName);

		      while (AdminUtils.topicExists(zkUtils, topicName)) {
		        System.out.println("Waiting for kafka to really delete topic ...");
		        try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    }
	}
	 
	 
	 /////////////////////////////
	 public Properties getTopicProperties(KafkaHandlerParms kafkaHandlerParms) {
		
		 ZkClient zkClient = new ZkClient(
					kafkaHandlerParms.zookeeperServers, 
					kafkaHandlerParms.sessionTimeoutMs,
					kafkaHandlerParms.connectionTimeoutMs, 
					ZKStringSerializer$.MODULE$);
			 
			ZkUtils zkUtils = new ZkUtils(
					zkClient, 
					new ZkConnection(kafkaHandlerParms.zookeeperServers), 
					kafkaHandlerParms.isSecureKafkaCluster);	
		 
		 try {
			 return AdminUtils.fetchEntityConfig(
					 zkUtils, 
					 ConfigType.Topic(), 
					 kafkaHandlerParms.topicName);
			 
	        } catch (IllegalArgumentException | KafkaException e) {
	        	throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
	        }
	    }
}
