package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer;

//Source
//https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/ProducerConfig.html

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

public interface IKafkaProducerConstants {
	
	public static String ACKS_CONFIG="all";
	public static Integer BATCH_SIZE_CONFIG= 20971520; //Micro batch size (Current setting is 20 20971520 MB)
	public static String BLOCK_ON_BUFFER_FULL_CONFIG = "";
	
	//public static String BOOTSTRAP_SERVERS_CONFIG = "node5.dke:6667";
	//From ClusteConfigurations
	public static String BOOTSTRAP_SERVERS_CONFIG = GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES;
	public static String ZOOKEEPER_SERVER_HOST_NAMES = GlobalClusterConfigurations.ZOOKEEPER_SERVER_HOST_NAMES;
	
	public static final String	BUFFER_MEMORY_CONFIG = "";
	public static final String	CLIENT_ID_CONFIG = "client1";
	
	//public static String COMPRESSION_TYPE_CONFIG = "gzip";
	public static String COMPRESSION_TYPE_CONFIG = "snappy";
	
	public static final String	CONNECTIONS_MAX_IDLE_MS_CONFIG = "";
	public static final String	INTERCEPTOR_CLASSES_CONFIG = "";
	
	public static final String	KEY_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";
	public static Integer LINGER_MS_CONFIG = 5;
	
	public static final String	MAX_BLOCK_MS_CONFIG = "";		
	public static final String	MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = "";
	
	public static final String	MAX_REQUEST_SIZE_CONFIG	= "2097152"; 	//2 MB 
	
	public static final String	METADATA_FETCH_TIMEOUT_CONFIG =	"";
	public static final String	METADATA_MAX_AGE_CONFIG	 = "";
	public static final String	METRIC_REPORTER_CLASSES_CONFIG = "";
	public static final String	METRICS_NUM_SAMPLES_CONFIG = "";
	public static final String	METRICS_SAMPLE_WINDOW_MS_CONFIG = "";
	public static final String	PARTITIONER_CLASS_CONFIG = "";
	public static final String	RECEIVE_BUFFER_CONFIG =	"";
	public static final String	RECONNECT_BACKOFF_MS_CONFIG = "";
	public static final String	REQUEST_TIMEOUT_MS_CONFIG = "";
	
	public static final String	RETRIES_CONFIG = "0";
	
	public static final String	RETRY_BACKOFF_MS_CONFIG = "";
	public static final String	SEND_BUFFER_CONFIG = "";
	public static final String	TIMEOUT_CONFIG = "";
	
	public static final String	VALUE_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";
}
