package lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration;

public interface GlobalClusterConfigurations {
//	public static String KAFKA_SERVER_HOST_NAMES = "localhost:9092";
//	public static String ZOOKEEPER_SERVER_HOST_NAMES = "localhost:2181";
	
	//Cluster
	public static String KAFKA_SERVER_HOST_NAMES = "node5.dke:6677,node6.dke:6677,node7.dke:6677";
	public static String ZOOKEEPER_SERVER_HOST_NAMES = "node1.dke:2181,node2.dke:2181,node5.dke:2181";
	
	public static final int ZOOKEEPER_SESSION_TIME_OUT_MS = 8000;
	public static final int ZOOKEEPER_CONNECTION_TIME_OUT_MS = 5000;
	
	public static final long TWO_SECONDS = 2000;
	
	//Topic Related
	public static String VIDEO_STREAM_TOPIC = "RVAS_1";
	public static String FEATURES_TOPIC = "RVAS_F_1";
	public static String ALERTS_TOPIC = "RVAS_A_1";
}
