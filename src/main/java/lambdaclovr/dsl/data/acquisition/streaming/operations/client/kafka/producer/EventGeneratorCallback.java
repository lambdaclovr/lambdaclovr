package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class EventGeneratorCallback implements Callback {
	
	private String cameraId;

	public EventGeneratorCallback(String cameraId) {
		super();
		this.cameraId = cameraId;
	}
	
	
	@Override
	public void onCompletion(RecordMetadata recordMetadata, Exception e) {
		if (e != null) {
			System.out.println("Error while producing message to topic :" + recordMetadata);
			e.printStackTrace();
		} else {
			String message = String.format("Sent message of Camers:%s to topic:%s partition:%s offset:%s", 
					cameraId ,recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
			System.out.println(message);
		}
	}
}