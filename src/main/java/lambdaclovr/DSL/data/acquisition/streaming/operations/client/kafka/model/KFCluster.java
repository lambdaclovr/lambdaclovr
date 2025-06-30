package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.entities</h3>
 * <h3>Class Name: KFCluster</h3>
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

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.KFClusterStatusName;

import java.util.List;

/**
 * It represents a Kafka cluster
 */
public class KFCluster {

    private KFClusterStatusName kfClusterStatus;
    private List<KFBroker> kfBrokers;

    /**
     *
     * @param kfClusterStatus Kafka cluster status
     * @param kfBrokers List of kafka brokers
     */
    public KFCluster(final KFClusterStatusName kfClusterStatus,
                     final List<KFBroker> kfBrokers) {
        this.kfClusterStatus = kfClusterStatus;
        this.kfBrokers = kfBrokers;
    }

    public List<KFBroker> getKFBrokers() {
        return kfBrokers;
    }

    public KFClusterStatusName getKfClusterStatus() {
        return kfClusterStatus;
    }
}
