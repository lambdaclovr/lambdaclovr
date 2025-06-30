package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka;

/**
 * It enumerates the Kafka cluster status names
 */
public enum KFClusterStatusName {
    /**
     * All Kafka brokers are up and running
     */
    OK,

    /**
     * At least one Kafka broker is up and running
     */
    WARNING,

    /**
     * All Kafka brokers are down
     */
    DOWN
}
