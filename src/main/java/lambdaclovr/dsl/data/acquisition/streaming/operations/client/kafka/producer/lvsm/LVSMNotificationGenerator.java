package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.lvsm;

import java.sql.Timestamp;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.EventGeneratorCallback;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.ProducerCreator;
import lambdaclovr.dsl.model.Anomaly;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: LVSMNotificationGenerator</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used for Lifelong video stream Monitering and sent the notification to Kafka.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 4.0
 * @since 2024-02-02
 * 
 **/
public class LVSMNotificationGenerator {
	private static Producer<String, String> producer = ProducerCreator.createProducer();

	/**
	 * Notification producer function for real-time video stream service
	 * @param notificationData Object
	 * @return
	 * @throws InterruptedException
	 */
	
	public static void produceNotification(Anomaly anomaly) throws InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		String stringNotificationData = "{}"; 
		
		//Map the NotificationData to JSON String 
		try {
			stringNotificationData = mapper.writeValueAsString(anomaly);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// Initiate the Callback
		EventGeneratorCallback eventGeneratorCallback = new EventGeneratorCallback(anomaly.getDsId());
		
		//Send the record
		ProducerRecord<String, String> data = new ProducerRecord<String, String>(
				anomaly.getTopicName(), 	//send to topic
				anomaly.getDsId(),  		//Key
				stringNotificationData);	//Value	
		
		//Sent to Kafka Broker and activate the Callback.
		producer.send(data, eventGeneratorCallback);
	}
	
	
	// Testing Notification 
	public static void main(String[] args) throws InterruptedException {
		
		// Set the notification Attribute
		Anomaly anomaly = new Anomaly()
				.setDsId("1")
				.setServiceId("1")
				.setTopicName("alerts");
		
		for (long i = 0; i < 10000; i++) {
			
			//Set the attributed of the Notification Data.
			anomaly.setTitle("Title")
					.setTitle("Motion Detected")
					.setMessage("Motion Detected Message")
					.setCols(50)
					.setRows(50)
					.setType(16)
					.setData(new byte[12])
					.setTimestamp(new Timestamp(System.currentTimeMillis()).toString())
					.setStartFrameTimestamp(new Timestamp(System.currentTimeMillis()).toString())
					.setEndFrameTimestamp(new Timestamp(System.currentTimeMillis()).toString())
					.setPosition("position");
						
				//Send the notification
				LVSMNotificationGenerator.produceNotification(anomaly);
				//Thread.sleep(500);
		}
		
	}
}
