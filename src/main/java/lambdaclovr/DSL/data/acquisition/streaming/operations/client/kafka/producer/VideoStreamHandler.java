package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.producer;

import java.util.Base64;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model.VideoFrameMessage;

public class VideoStreamHandler {
	static Producer<String, String> producer;
	static double MessagePerSecond = 0;
	

	// Load OpenCV native library
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// nu.pattern.OpenCV.loadShared();
	}

	public static void videoStreamEventProducer(
			// Producer<String, String> producer,
			String topicName, String cameraId, String cameraURL) throws Exception {

		// Create new Producer
		producer = ProducerCreator.createProducer();

		// Set the constant attributes of the message
		VideoFrameMessage videoFrameMessage = new VideoFrameMessage().setTopicName(topicName).setCameraId(cameraId)
				.setCameraURL(cameraURL);
		// System.out.println(videoFrameMessage.getTopicName());

		// Camera object and testing camera
		VideoCapture camera = null;
		if (StringUtils.isNumeric(videoFrameMessage.getCameraURL())) {
			camera = new VideoCapture(Integer.parseInt(videoFrameMessage.getCameraURL()));
		} else {
			camera = new VideoCapture(videoFrameMessage.getCameraURL());
		}

		// System.out.println(videoFrameMessage.getCameraURL());

		// check camera working
		if (!camera.isOpened()) {
			Thread.sleep(5000);
			if (!camera.isOpened()) {
				throw new Exception(
						"Error opening cameraId " + videoFrameMessage.getCameraId() + " with URL = " + cameraURL);
			}
		}

		// Initializations
		// Callback to confirm the message
		EventGeneratorCallback eventGeneratorCallback = new EventGeneratorCallback(cameraId);

		// OpenCV mat
		Mat mat = new Mat();

		Gson gson = new Gson();
		
		try {
			
			long start = System.currentTimeMillis();
			long end = start + 30*1000; // 60 seconds * 1000 ms/sec
			//while (camera.read(mat)) {
			while (true) {	
				
				System.out.println("------------------------------");
				Long startTime = System.currentTimeMillis();
				
				// Frame resize
				Imgproc.resize(mat, mat, new Size(480, 320), 0, 0, Imgproc.INTER_CUBIC);

				// Set the changing attributed of the message
				videoFrameMessage.setCols(mat.cols());
				videoFrameMessage.setRows(mat.rows());
				videoFrameMessage.setType(mat.type());
				videoFrameMessage.setData(new byte[(int) (mat.total() * mat.channels())]);
				mat.get(0, 0, videoFrameMessage.getData());
				videoFrameMessage.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

				// Convert VideoFrameMessage object to JSon Object and then to string
				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("cameraId", videoFrameMessage.getCameraId());
				jsonObject.addProperty("timestamp", videoFrameMessage.getTimestamp());
				jsonObject.addProperty("rows", videoFrameMessage.getRows());
				jsonObject.addProperty("cols", videoFrameMessage.getCols());
				jsonObject.addProperty("type", videoFrameMessage.getType());
				jsonObject.addProperty("data", Base64.getEncoder().encodeToString(videoFrameMessage.getData()));
				
				jsonObject.addProperty("vlbp", videoFrameMessage.getVLBP());
				String jsonString = gson.toJson(jsonObject);

				Long timeTaken = System.currentTimeMillis() - startTime;
				System.out.println("Image JsonString Message Composing Time:" + timeTaken);
				
				
				// System.out.println(videoFrameMessage.getTopicName() +
				// videoFrameMessage.getCameraId());
				// Composing ProducerRecord
				ProducerRecord<String, String> messageData = new ProducerRecord<String, String>(
						videoFrameMessage.getTopicName(), // send to topic
						videoFrameMessage.getCameraId(), // Key
						jsonString); // Value

				// Send the message
				producer.send(messageData, eventGeneratorCallback);

				Long timeTakenAll = System.currentTimeMillis() - startTime;
				System.out.println("Message Total Time:" + timeTakenAll);
				
				//////////////////////////////////////////////////////////////////////////////
				// Size of each frame before compression
				int dataSize = jsonString.length();
				byte[] dataSize1 = jsonString.getBytes();
				System.out.println("Size: " + dataSize);
				System.out.println("Size in Bytes: " + dataSize1.length);

				// Size of each frame after compression
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);
				gzip.write(jsonString.getBytes());
				gzip.close();

				System.out.println("Compressed Size: " + out.toString().getBytes().length);
				//////////////////////////////////////////////////////////////////////////////
				System.out.println("------------------------------");
				MessagePerSecond++;	
				System.out.println(MessagePerSecond);
				
				if(System.currentTimeMillis() > end ) {
					//System.exit(0);
				}
				
			}
		} finally {
			producer.close();
			mat.release();
		}
	}
}