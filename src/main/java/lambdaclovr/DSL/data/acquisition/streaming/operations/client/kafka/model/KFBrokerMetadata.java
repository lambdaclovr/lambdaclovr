/**
 * Copyright (c) 2017 McAfee LLC - All Rights Reserved
 */

package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model;



/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.entities</h3>
 * <h3>Class Name: KFBrokerMetadata</h3>
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

public class KFBrokerMetadata {


    private int brokerId = 0;
    private String host = "";
    private int port = 0;
    private String connectionString = "";
    private String securityProtocol = "";

    public void setBrokerId(final int brokerId) {
        this.brokerId = brokerId;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setConnectionString(final String connectionString) {
        this.connectionString = connectionString;
    }

    public void setSecurityProtocol(final String securityProtocol) {
        this.securityProtocol = securityProtocol;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getSecurityProtocol() {
        return securityProtocol;
    }

}
