package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer.VideoStreamHandler;

public class VideoStreamHandlerExample {
	public static void main(String[] args) throws Exception {
		
		String topicName = "RVAS_F_1"; // For Face detection
		String cameraId = "10";
		String cameraURL = "C:\\cams\\3.mp4";
		
		VideoStreamHandler.videoStreamEventProducer(topicName, cameraId, cameraURL);
	}
} 