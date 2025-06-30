package lambdaclovr.dsl.service.kafka;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.kafka.clients.producer.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model.KafkaProducerCamModel;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.ProducerCreator;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.VideoStreamHandler;

@Path("/KafkaVideoStream")
@Api("/Kafka Video Stream")
@SwaggerDefinition(tags = { @Tag(name = "Test Service", description = "REST Endpoint for Product Service") })
public class KafkaVideoStreamService {

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new video stream to a topic", notes = "RolesAllowed: Session user (Admin, Developer).")
	public void startVideoStreamEvent(KafkaProducerCamModel KafkaProducerCamModel) throws Exception {
		
		System.out.println(KafkaProducerCamModel.getTopicName());
		System.out.println(KafkaProducerCamModel.getCameraId());
		System.out.println(KafkaProducerCamModel.getCameraUrl());
		
		//Create Producer
		Producer<String, String> producer = ProducerCreator.createProducer();
		
		//Kafka Topic Name, where the stream will go.
		String topicName = KafkaProducerCamModel.getTopicName(); // For Face detection
		
		//ID of the Camera from the DB.
		String cameraId = KafkaProducerCamModel.getCameraId();	
		
		//Currently, we use videos as a stream from local directory. 
		String cameraURL = "F:\\cams\\" + KafkaProducerCamModel.getCameraUrl() + ".mp4";
		
		VideoStreamHandler.videoStreamEventProducer(topicName, cameraId, cameraURL);
		
		//return KafkaProducerCamModel;
	}
}