package lambdaclovr.dsl.data.acquisition.streaming.operations.client.common;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.KafkaException;

import kafka.admin.AdminOperationException;
import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.admin.RackAwareMode;
import kafka.cluster.Broker;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;
import scala.collection.Map;
import scala.collection.Seq;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.TopicOperationException;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.common</h3>
 * <h3>Class Name: ClusterTools</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-27-11
 * 
 * */

public class ClusterTools {

    /**
     * Change topic configuration
     *
     * @param connection zookeeper util API
     * @param topicName  topic name
     * @param configs    topic properties
     */
    public void overrideTopicProperties(final ZkUtils connection, final String topicName, final Properties configs) {
        try {
            AdminUtils.changeTopicConfig(connection, topicName, configs);
        } catch (AdminOperationException | KafkaException e) {
            throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
        }
    }

    /**
     * Get topic configuration
     *
     * @param connection connection
     * @param topicName  topic name
     * @return topic properties
     */
    public Properties getTopicProperties(final ZkUtils connection, final String topicName) {
        try {
        	return AdminUtils.fetchEntityConfig(connection, ConfigType.Topic(), topicName);
        } catch (IllegalArgumentException | KafkaException e) {
            throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
        }
    }

    /**
     * Verify if the topic exists
     *
     * @param connection connection
     * @param topicName  topic name
     * @return true if topic exists otherwise return false
     */
    public boolean topicExists(final ZkUtils connection, final String topicName) {
        return AdminUtils.topicExists(connection, topicName);
    }

    /**
     * Get a list of Kafka Brokers
     *
     * @param connection connection
     * @return List of Kafka brokers
     */
    public List<Broker> getKafkaBrokers(final ZkUtils connection) {
        return JavaConversions.seqAsJavaList(connection.getAllBrokersInCluster());
    }


    /**
     * Create a topic
     *
     * @param connection        Connection
     * @param topicName         Topic name
     * @param partitions        The number of partitions for the topic being created
     * @param replicationFactor The replication factor for each partition in the topic being created
     * @param topicProperties   A topic configuration override for an existing topic
     * @throws TopicOperationException if topic was not created.
     */
    public void createTopic(final ZkUtils connection, 
    						final String topicName,
                            final int partitions,
                            final int replicationFactor,
                            final Properties topicProperties) {

        try {
            AdminUtils.createTopic(
            		connection,
                    topicName,
                    partitions,
                    replicationFactor,
                    topicProperties, 
                    RackAwareMode.Enforced$.MODULE$);

        } catch (IllegalArgumentException | KafkaException | AdminOperationException e) {
            throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
        }
    }
       
    /**
     * Delete a topic
     *
     * @param connection  Connection
     * @param topicName   Topic name
     * @throws TopicOperationException if topic was not created.
     */
    public void deleteTopic(final ZkUtils connection, final String topicName){
        
    	try {
        	AdminUtils.deleteTopic(connection, topicName);
        	
        } catch (IllegalArgumentException | KafkaException | AdminOperationException e) {
            throw new TopicOperationException(topicName, e.getMessage(), e, this.getClass());
        }
    }
    
//Need verification of the following two functions.    
    /**
     * Change the number of partitions
     *
     * @param connection Connection
     * @param topic topicName       
     * @param partitions        The number of partitions for the topic being created
     * @param replicationFactor The replication factor for each partition in the topic being created
     */
    @SuppressWarnings("unused")
	private static void changeTopicPartition(final ZkUtils connection, 
									    	 String topic, 
									    	 int partitions, 
									    	 int replicationFactor) {
        
    	//Get proxy metadata information
        Seq<BrokerMetadata> brokerMeta = AdminUtils.getBrokerMetadatas(connection, AdminUtils
                .getBrokerMetadatas$default$2(), AdminUtils.getBrokerMetadatas$default$3());
        // Generate partition copy allocation scheme
        Map<Object, Seq<Object>> replicaAssign = AdminUtils.assignReplicasToBrokers(brokerMeta, partitions,
        		replicationFactor, AdminUtils.assignReplicasToBrokers$default$4(), AdminUtils
                        .assignReplicasToBrokers$default$5());
        // Modify the partition copy allocation scheme
        AdminUtils.createOrUpdateTopicPartitionAssignmentPathInZK(connection, topic, replicaAssign, null, true);
    }
}
