package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicService;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.TopicServiceBuilder;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.TopicOperationException;

import java.time.Instant;

public class DeleteTopicExample {

    //private static final String ZOOKEEPER_SERVER_HOST_NAMES = "node1.khu:2181, node3.khu:2181, node4.khu:2181, node5.khu:2181";
	private static final String ZOOKEEPER_SERVER_HOST_NAMES = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
    private static final int ZOOKEEPER_SESSION_TIME_OUT_MS = 8000;
    private static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = 5000;


    private void startExample() {

        try (TopicService topicService = new TopicServiceBuilder(ZOOKEEPER_SERVER_HOST_NAMES)
                .withZKConnectionTimeout(ZOOKEEPER_CONNECTION_TIME_OUT_MS)
                .withZKSessionTimeout(ZOOKEEPER_SESSION_TIME_OUT_MS)
                .build()) {

            //final String topicName = "Camera1" + Instant.now().getEpochSecond();
            final String topicName = "cam";

            if(topicService.topicExists(topicName)) {
                topicService.deleteTopic(topicName);
                
                System.out.println("Topic Deleted: " + topicName);
            } else {
                System.out.println("Topic does not exist: " + topicName);
            }


        } catch (TopicOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("UNKNOWN ERROR: " + e.getMessage());
        }

    }

    public static void main(final String[] args) {
        new DeleteTopicExample().startExample();
    }
}
