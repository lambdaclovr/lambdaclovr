package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicService;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicServiceBuilder;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

import java.util.List;

/**
 * It get all the topic. If no topics are available, a empty list is returned.
 * 
 * <pre>
 * {
 * 	&#64;code
 * 	public class GetAllTopicsExample {
 * 		private static final String ZOOKEEPER_SERVER_HOST_NAMES = "zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181";
 * 		private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = 8000;
 * 		private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = 5000;
 *
 * 		private void startExample() {
 * 			try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
 * 					.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
 * 					.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {
 *
 * 				List<String> allTopics = topicService.getAllTopics();
 * 				System.out.println("Topics: " + allTopics);
 * 			} catch (Exception e) {
 * 				System.out.println("UNKNOWN ERROR: " + e.getMessage());
 * 			}
 * 		}
 *
 * 		public static void main(final String[] args) {
 * 			new GetAllTopicsExample().startExample();
 * 		}
 * 	}
 * }
 * </pre>
 */

public class GetAllTopics {
	// private static final String ZOOKEEPER_SERVER_HOST_NAMES =
	// "zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181";
	private static final String ZOOKEEPER_SERVER_HOST_NAMES = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
	private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_SESSION_TIME_OUT_MS;
	private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_CONNECTION_TIME_OUT_MS;

	private void startExample() {
		try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
				.withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
				.withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS).build()) {
				
			List<String> allTopics = topicService.getAllTopics();	
			System.out.println("Topics: " + allTopics);
		} catch (Exception e) {
			System.out.println("UNKNOWN ERROR: " + e.getMessage());
		}
	}

	public static void main(final String[] args) {
		new GetAllTopics().startExample();
	}
}
