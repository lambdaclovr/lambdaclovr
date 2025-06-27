package lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer.video.analytics;

import java.awt.image.BufferedImage;
import java.util.Base64;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.consumer.VideoEventData;

public class FrameOperations {

	// Save image file
	@SuppressWarnings("unused")
	private static void saveImage(Mat mat, VideoEventData ed, String outputDir) {
		String imagePath = outputDir + ed.getCameraId() + "-T-" + ed.getTimestamp().getTime() + ".png";
		// logger.warn("Saving images to "+imagePath);
		System.out.println("Saving images to " + imagePath);
		boolean result = Imgcodecs.imwrite(imagePath, mat);
		if (!result) {
			// logger.error("Couldn't save images to path "+outputDir+".Please check if this
			// path exists. This is configured in processed.output.dir key of property
			// file.");
			System.out.println("Couldn't save images to path " + outputDir
					+ ".Please check if this path exists. This is configured in processed.output.dir key of property file.");
		}
	}

	// Bytes to Mat
	@SuppressWarnings("unused")
	private static Mat getMat(VideoEventData ed) throws Exception {
		Mat mat = new Mat(ed.getRows(), ed.getCols(), ed.getType());
		mat.put(0, 0, Base64.getDecoder().decode(ed.getData()));
		return mat;
	}

	// Convert Mat to Image
	public static BufferedImage mat2Img(Mat mat) {
		BufferedImage bufferedImage;
		byte[] data = new byte[320 * 240 * (int) mat.elemSize()];
		int type;
		mat.get(0, 0, data);

		if (mat.channels() == 1)
			type = BufferedImage.TYPE_BYTE_GRAY;
		else
			type = BufferedImage.TYPE_3BYTE_BGR;

		bufferedImage = new BufferedImage(320, 240, type);

		bufferedImage.getRaster().setDataElements(0, 0, 320, 240, data);
		return bufferedImage;
	}
	
	//Bytes to Image
	public static BufferedImage bytes2Image(VideoEventData ed) throws Exception
	{
		Mat mat = getMat(ed);
		return mat2Img(mat);
		
	}
}
