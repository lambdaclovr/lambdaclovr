package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model;


import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.KFBrokerStatusName;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.entities</h3>
 * <h3>Class Name: KFBroker</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-27-11
 * 
 */
public class KFBroker {

    private KFBrokerStatusName status;
    private String brokerName;
    private KFBrokerMetadata brokerMetadata;

    /**
     * @param brokerName broker name
     * @param brokerMetadata Kafka broker metadata
     * @param status Kafka broker status
     */
    public KFBroker(final String brokerName,
                    final KFBrokerMetadata brokerMetadata,
                    final KFBrokerStatusName status) {

        this.brokerName = brokerName;
        this.brokerMetadata = brokerMetadata;
        this.status = status;
    }


    public String getBrokerName() {
        return brokerName;
    }

    public KFBrokerStatusName getStatus() {
        return status;
    }

    public String getHostName() {
        return brokerMetadata.getHost();
    }

    public int getId() {
        return brokerMetadata.getBrokerId();
    }

    public int getPort() {
        return brokerMetadata.getPort();
    }

    public String getConnectionString() {
        return brokerMetadata.getConnectionString();
    }

    public String getSecurityProtocol() {
        return brokerMetadata.getSecurityProtocol();
    }

}
