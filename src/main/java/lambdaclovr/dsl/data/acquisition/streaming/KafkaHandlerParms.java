package lambdaclovr.dsl.data.acquisition.streaming;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.data.acquisition.stream</h3>
 * <h3>Class Name: KafkaHandlerParms</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as an ALGORITHM model.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/
public class KafkaHandlerParms {

	// Topic name must be unique.
	public String topicName = "";
	
	//If topic created successfuly then set this to 1 otherwize to 0
	public boolean isTopicCreatedSuccessfully = false;
	
	// String seperated by ","
	// Example: node1.khu:2181, node2.khu:2181
	public String zookeeperServers = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
	public int sessionTimeoutMs = 10 * 1000;
	public int connectionTimeoutMs = 8 * 1000;
	public boolean isSecureKafkaCluster = false;
	
	public int partitions = 1;

	// this value is equal to the number of Kafka brockers.
	public int replicationFactor = 1;

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getZookeeperServers() {
		return zookeeperServers;
	}

	public void setZookeeperServers(String zookeeperServers) {
		this.zookeeperServers = zookeeperServers;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public boolean isSecureKafkaCluster() {
		return isSecureKafkaCluster;
	}

	public void setSecureKafkaCluster(boolean isSecureKafkaCluster) {
		this.isSecureKafkaCluster = isSecureKafkaCluster;
	}

	public int getPartitions() {
		return partitions;
	}

	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}

	public int getReplications() {
		return replicationFactor;
	}

	public void setReplications(int replicationFactor) {
		this.replicationFactor = replicationFactor;
	}
}
