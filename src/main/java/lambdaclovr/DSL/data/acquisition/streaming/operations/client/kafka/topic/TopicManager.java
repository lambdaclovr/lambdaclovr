package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.topic;

import java.util.List;
import java.util.Properties;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicService;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicServiceBuilder;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.TopicOperationException;

public class TopicManager {

	private static final String ZOOKEEPER_SERVER_HOST_NAMES = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
	private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_SESSION_TIME_OUT_MS;
	private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_CONNECTION_TIME_OUT_MS;
	private static final int PARTITIONS = 1;
	private static final int REPLICATION_FACTOR = 1;
	private static boolean isDeleted = false;

	/**
	 * Create a topic
	 * 
	 * @param topicName
	 *            Topic name
	 * @param partitions
	 *            The number of partitions for the topic being created
	 * @param replicationFactor
	 *            The replication factor for each partition in the topic being
	 *            created
	 */

	public static void createTopic(String topicName, int PARTITIONS, int REPLICATION_FACTOR) {

		try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
				.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
				.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {

			if (!topicService.topicExists(topicName)) {
				topicService.createTopic(topicName, PARTITIONS, REPLICATION_FACTOR);
				System.out.println("Topic Created: " + topicName);
			} else {
				System.out.println("Topic already exists: " + topicName);
			}

		} catch (TopicOperationException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("UNKNOWN ERROR: " + e.getMessage());
		}
	}

	/**
	 * Delete a topic
	 * 
	 * @param topicName
	 *            Topic name
	 */
	public static boolean deleteTopic(String topicName) {
		try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
				.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
				.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {

			if (topicService.topicExists(topicName)) {
				topicService.deleteTopic(topicName);
				System.out.println("Topic Deleted: " + topicName);
				isDeleted = true;
			} else {
				System.out.println("Topic does not exist: " + topicName);
				isDeleted = false;
			}

		} catch (TopicOperationException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("UNKNOWN ERROR: " + e.getMessage());
		}
		return isDeleted;
	}

	/**
	 * Get All topic from Kafka Broker
	 * 
	 * @param topicName
	 *            Topic name
	 * 
	 * @return List of topics
	 */
	public static List<String> getAllTopics() {

		
		List<String> allTopics = null;
		try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
				.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
				.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {

			allTopics = topicService.getAllTopics();
			System.out.println("Topics: " + allTopics);
			
		} catch (Exception e) {
			System.out.println("UNKNOWN ERROR: " + e.getMessage());
		}

		return allTopics;
	}

	/**
	 * Get details of a topic
	 * 
	 * @param topicName
	 *            Topic name
	 * 
	 * @return Topic details
	 */
	public static void getTopicDetails(String topicName) {

		try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
				.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
				.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {

			if (topicService.topicExists(topicName)) {
				System.out.println("Topic exist " + topicName);

				Properties properties = topicService.getTopicProperties(topicName);
				// Properties properties = ClusterTools.getTopicProperties(topicName);

				for (Object key : properties.keySet()) {
					System.out.println(key + ":" + properties.get(key));
				}

			} else {
				System.out.println("Topic does not exist: " + topicName);
			}

		} catch (TopicOperationException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("UNKNOWN ERROR: " + e.getMessage());
		}
	}

}
