package lambdaclovr.dsl.data.acquisition.streaming.operations.client;

import java.util.Map;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception.ZKMonitorException;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.zookeeper.ZKClusterHealthName;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.zookeeper.ZKClusterWatcher;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.zookeeper.ZKMonitorCallback;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.zookeeper.entities.ZKCluster;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client</h3>
 * <h3>Class Name: ZookeeperMonitor</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-27-11
 * 
 * It is the main API to monitor a Zookeeper ensemble.
 *
 * The following example creates a Zookeeper monitor instance and get the cluster health check
 * <pre>{@code
 *   public static void main(String[] args){
 *       final String zkHosts = "zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181";
 *       ZookeeperMonitor zkMonitor = new ZookeeperMonitorBuilder(zkHosts)
 *               .withZKPollingInitialDelayTime(1000)
 *               .withZKPollingInitialDelayTime(0)
 *               .withZKSessionTimeout(8000)
 *               .withZKMonitorListener(new ZKMonitorCallback() {
 *                   public void onNodeUp(String zkNodeName) {}
 *                   public void onNodeDown(String zkNodeName) {}
 *                   public void onGetQuorum() {}
 *                   public void onLackOfQuorum() {}
 *               }).build();
 *       zkMonitor.start();
 *       final ZKClusterHealthName health = zkMonitor.getHealth();
 *       if(health == ZKClusterHealthName.OK) {
 *           System.out.println("All Zookeeper nodes are up and running");
 *       }
 *   }
 * }</pre>
 * 
 * @see lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples.ZKMonitorPollingStatusExample
 * @see lambdaclovr.dsl.data.acquisition.streaming.operations.client.ZKMonitorEventListenerExample
 * @see lambdaclovr.dsl.data.acquisition.streaming.operations.client.ZKMonitorPollingAndEventListenerExample
 * @see lambdaclovr.dsl.data.acquisition.streaming.operations.client.ZKMonitorClusterHealthExample
 * @see lambdaclovr.dsl.data.acquisition.streaming.operations.client.ZKMonitorNodeStatisticsExample
 */
public final class ZookeeperMonitor implements AutoCloseable {

    private final ZKClusterWatcher zkClusterWatcher;

    /**
     * Constructs a zookeeper monitoring able to send zookeeper notifications to client.
     *
     * @param configuration     configuration
     * @param zkMonitorListener a listener implemented by the client to be notified when zookeeper quorum event occurs
     * @throws IllegalArgumentException if configuration is null or any of arguments properties is missing or invalid
     *
     * @see ZKMonitorCallback
     */
    public ZookeeperMonitor(final Map<String, String> configuration,
                            final ZKMonitorCallback zkMonitorListener) {

        this.zkClusterWatcher = new ZKClusterWatcher(configuration, zkMonitorListener);
    }


    /**
     * Constructs a zookeeper monitoring unable to send zookeeper notifications to client.
     *
     * @param configuration configuration
     * @throws IllegalArgumentException if configuration is null or any of arguments properties is missing or invalid
     */
    public ZookeeperMonitor(final Map<String, String> configuration) {
        this(configuration, null);
    }


    /**
     * Get zookeeper cluster status.
     * A polling mechanism could be implemented by clients to get zookeeper cluster status.
     *
     * @return {@link ZKCluster}
     * @throws IllegalStateException if {@link ZookeeperMonitor#start()} method was not called.
     */
    public ZKCluster getCluster() {
        return zkClusterWatcher.getCluster();
    }


    /**
     * Start zookeeper monitoring
     *
     * @throws ZKMonitorException when connection fails
     */
    public void start() {
        try {
            zkClusterWatcher.start();
        } catch (Exception e) {
            throw new ZKMonitorException("Could not start zookeeper monitoring service", e, this.getClass());
        }
    }


    /**
     * Stop zookeeper monitoring
     */
    public void stop() {
        zkClusterWatcher.stop();
    }


    /**
     * Get the general health of zookeeper cluster. If zookeeper cluster has not quorum or at least a node is unreachable then
     * it returns NO_QUORUM or WARNING respectively. Otherwise it returns OK.
     *
     * @return {@link ZKClusterHealthName} zookeeper status health
     * @throws IllegalStateException if {@link ZookeeperMonitor#start()} was not called.
     *
     * @see ZKClusterHealthName for status name meaning
     */
    public ZKClusterHealthName getHealth() {
        return zkClusterWatcher.getHealth();
    }

    /**
     * Stop monitor by using try-with-resources statement
     *
     * @throws Exception if cannot stop Zookeeper monitor
     */
    @Override
    public void close() throws Exception {
        stop();
    }
}
