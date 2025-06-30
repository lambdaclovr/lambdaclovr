import kafka.admin.RackAwareMode;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import org.apache.kafka.common.utils.Time;
import scala.Option;
import scala.collection.JavaConverters;
import scala.collection.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaClient {
    private static final KafkaClient INSTANCE = new KafkaClient();

    private static KafkaZkClient kafkaZkClient;
    private static AdminZkClient adminZkClient;

    public KafkaClient() {
        String zookeeperHost = "host:port,host:port,...";
        Boolean isSecure = false;
        int sessionTimeoutMs = 200000;
        int connectionTimeoutMs = 15000;
        int maxInFlightRequests = 10;
        Time time = Time.SYSTEM;
        String metricGroup = "kafka.server";
        String metricType = "SessionExpireListener";

        init(zookeeperHost, isSecure, sessionTimeoutMs, connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType, null);
    }

    public static KafkaClient getInstance() {
        return INSTANCE;
    }

    public void init(String propertiesFile) {
        init(PropertyFileReader.readPropertyFile("kafka.properties"));
    }

    public void init(Properties properties) {
        String zookeeperHost = properties.getProperty("zookeeper.host");
        Boolean isSucre = Boolean.parseBoolean(properties.getProperty("isSecure"));
        int sessionTimeoutMs = Integer.parseInt(properties.getProperty("session.timeoutMs"));
        int connectionTimeoutMs = Integer.parseInt(properties.getProperty("connection.timeoutMs"));
        int maxInFlightRequests = Integer.parseInt(properties.getProperty("max.inflight.requests"));
        Time time = Time.SYSTEM;
        String metricGroup = properties.getProperty("metric.group");
        String metricType = properties.getProperty("metric.type");

        init(zookeeperHost, isSucre, sessionTimeoutMs, connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType, null);
    }

    private void init(String zookeeperHost, Boolean isSecure, int sessionTimeoutMs, int connectionTimeoutMs, int maxInFlightRequests, Time time, String metricGroup, String metricType, final Option<String> name) {
        kafkaZkClient = KafkaZkClient.apply(zookeeperHost, isSecure, sessionTimeoutMs, connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType, null);
        adminZkClient = new AdminZkClient(kafkaZkClient);
    }

    public void createTopic(String topicName, int numPartitions, int replicationFactor, Properties topicConfig) {
        adminZkClient.createTopic(topicName, numPartitions, replicationFactor, topicConfig, RackAwareMode.Disabled$.MODULE$);
    }

    public List<String> listTopics() {
        Set<String> allTopics = kafkaZkClient.getAllTopicsInCluster();
        java.util.Set<String> set = JavaConverters.asJava(allTopics);
        return new ArrayList(set);
    }

    public void deleteTopic(String topicName) {
        adminZkClient.deleteTopic(topicName);
    }

    public Boolean topicExists(String topicName) {
        return kafkaZkClient.topicExists(topicName);
    }
}
