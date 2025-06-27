package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicService;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicServiceBuilder;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.TopicOperationException;

/**
 * It created a topic by using a topic name based on current time.
 * <pre>
 * {@code
 * public class CreateTopicExample {
 *
 *    private static final String ZOOKEEPER_SERVER_HOST_NAMES = "zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181";
 *    private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = 8000;
 *    private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = 5000;
 *    private static final int PARTITIONS = 1;
 *    private static final int REPLICATION_FACTOR = 1;
 *
 *
 *    private void startExample() {
 *
 *        try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
 *                .withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
 *                .withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS)
 *                .build()) {
 *
 *            final String topicName = "MyTopic" + Instant.now().getEpochSecond();
 *
 *            if(!topicService.topicExists(topicName)) {
 *                topicService.createTopic(topicName,
 *                        PARTITIONS,
 *                        REPLICATION_FACTOR,
 *                        new Properties());
 *                System.out.println("Topic Created: " + topicName);
 *            } else {
 *                System.out.println("Topic already exists: " + topicName);
 *            }
 *
 *        } catch (TopicOperationException e) {
 *            System.out.println("ERROR: " + e.getMessage());
 *        } catch (Exception e) {
 *            System.out.println("UNKNOWN ERROR: " + e.getMessage());
 *        }
 *
 *    }
 *
 *    public static void main(final String[] args) {
 *        new CreateTopicExample().startExample();
 *    }
 *}
 *}
 * </pre>
 */

public class CreateTopic {

    //private static final String ZOOKEEPER_SERVER_HOST_NAMES = "zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181";
    private static final String ZOOKEEPER_SERVER_HOST_NAMES = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
    private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_SESSION_TIME_OUT_MS;
    private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = GlobalClusterConfigurations.ZOOKEEPER_CONNECTION_TIME_OUT_MS;
    private static final int PARTITIONS = 8;
    private static final int REPLICATION_FACTOR = 3;


    private void startExample() {

        try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
                .withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
                .withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS)
                .build()) {

            //final String topicName = "Camera1" + Instant.now().getEpochSecond();
            final String topicName = "RVAS_A_2";

            if(!topicService.topicExists(topicName)) {
                topicService.createTopic(topicName,
                        PARTITIONS, 
                        REPLICATION_FACTOR);
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

    public static void main(final String[] args) {
        new CreateTopic().startExample();
    }
}
