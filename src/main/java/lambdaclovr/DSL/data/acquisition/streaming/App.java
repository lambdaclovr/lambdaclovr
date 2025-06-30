package lambdaclovr.dsl.data.acquisition.streaming;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		KafkaHandlerParms kafkaHandlerParms = new KafkaHandlerParms();
		kafkaHandlerParms.topicName ="cam";
		KafkaHandler kafkaHandler = new KafkaHandler();
//		kafkaHandler.createTopic(kafkaHandlerParms);
		kafkaHandler.getTopicProperties(kafkaHandlerParms);
		
//		Producer<String, String> producer = KafkaProducerProperties.createProducer();
//		String topicName = "cam"; 
//		String cameraId = "1";
//		String cameraURL = "F:/Ubuntu/backup/siyamul/Videos/f.mp4";
//		
//		VideoStreamHandler.videoStreamEventProducer(producer, topicName, cameraId, cameraURL);
	}
}
