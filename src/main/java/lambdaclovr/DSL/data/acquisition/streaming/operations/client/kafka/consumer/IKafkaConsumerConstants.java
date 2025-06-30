package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration.GlobalClusterConfigurations;

//Sources
//https://kafka.apache.org/0100/javadoc/org/apache/kafka/clients/consumer/ConsumerConfig.html

public interface IKafkaConsumerConstants {

	public static String AUTO_COMMIT_INTERVAL_MS_CONFIG = "";
	public static String AUTO_OFFSET_RESET_CONFIG = "earliest";
	public static String AUTO_OFFSET_RESET_DOC = "";
	public static String BOOTSTRAP_SERVERS_CONFIG = GlobalClusterConfigurations.KAFKA_SERVER_HOST_NAMES;
	public static String CHECK_CRCS_CONFIG = "";
	public static String CLIENT_ID_CONFIG = "";
	public static String CONNECTIONS_MAX_IDLE_MS_CONFIG = "";
	public static String DEFAULT_EXCLUDE_INTERNAL_TOPICS = "";
	public static String DEFAULT_MAX_PARTITION_FETCH_BYTES = "1097152";
	public static String ENABLE_AUTO_COMMIT_CONFIG = "true";
	public static String EXCLUDE_INTERNAL_TOPICS_CONFIG = "";
	public static String FETCH_MAX_WAIT_MS_CONFIG = "";
	public static String GROUP_ID_CONFIG = "group";
	public static String HEARTBEAT_INTERVAL_MS_CONFIG = "";
	public static String INTERCEPTOR_CLASSES_CONFIG = "";
	public static String INTERCEPTOR_CLASSES_DOC = "";
	public static String KEY_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
	public static String KEY_DESERIALIZER_CLASS_DOC = "";
	public static String MAX_PARTITION_FETCH_BYTES_CONFIG = "";
	public static String FETCH_MIN_BYTES_CONFIG = "";
	public static Integer MAX_POLL_RECORDS_CONFIG = 1;
	public static String METADATA_MAX_AGE_CONFIG = "1";
	public static String METRIC_REPORTER_CLASSES_CONFIG = "";
	public static String METRICS_NUM_SAMPLES_CONFIG = "";
	public static String METRICS_SAMPLE_WINDOW_MS_CONFIG = "";
	public static String PARTITION_ASSIGNMENT_STRATEGY_CONFIG = "";
	public static String RECEIVE_BUFFER_CONFIG = "";
	public static String RECONNECT_BACKOFF_MS_CONFIG = "";
	public static String REQUEST_TIMEOUT_MS_CONFIG = "";
	public static String RETRY_BACKOFF_MS_CONFIG = "";
	public static String SEND_BUFFER_CONFIG = "";
	public static String SESSION_TIMEOUT_MS_CONFIG = "";
	public static String VALUE_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
	public static String VALUE_DESERIALIZER_CLASS_DOC = "";

	
	//KAFKA VARIABLES
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=10;
	public static String KAFKA_MAX_POLL_RECORDS = "500";
	public static String KAFKA_MAX_PARTITION_FETCH_BYTES = "1097152";
	
	public static Integer MESSAGE_COUNT=1000;
	
}
