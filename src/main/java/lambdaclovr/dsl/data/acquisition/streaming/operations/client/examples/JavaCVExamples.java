package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class JavaCVExamples {
	static File file = new File("F:\\cams\\output\\1.txt");
	static String outputDir = "F:\\cams\\output\\";
	// load OpenCV native lib
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String cameraURL = "F:\\cams\\1.mp4";
		// Camera object and testing camera
		VideoCapture camera = null;
		if (StringUtils.isNumeric(cameraURL)) {
			camera = new VideoCapture(Integer.parseInt(cameraURL));
		} else {
			camera = new VideoCapture(cameraURL);
		}

		System.out.println(cameraURL);

		// check camera working
		if (!camera.isOpened()) {
			Thread.sleep(5000);
			if (!camera.isOpened()) {
				throw new Exception("Error opening cameraId " + cameraURL + " with URL = " + cameraURL);
			}
		}

		// OpenCV mat
		Mat mat = new Mat();
		Mat matImg = new Mat();

		try {

			OutputStream os = new FileOutputStream(file);

			while (camera.read(mat)) {
				// Frame resize imag->bytes
				Imgproc.resize(mat, mat, new Size(640, 480), 0, 0, Imgproc.INTER_CUBIC);
				int cols = mat.cols();
				int rows = mat.rows();
				int type = mat.type();
				System.out.println(type);

				// Frame to bytes
				byte[] data = new byte[(int) (mat.total() * mat.channels())];
				mat.get(0, 0, data);
				String timestamp = new Timestamp(System.currentTimeMillis()).toString();

				// os.write(data);
				// os.close()
				;
				System.out.println(data);

				// bytes
				matImg = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

				Imgcodecs.imwrite("test.", matImg);

				Thread.sleep(1000000);

			}
		} finally {
			mat.release();
		}

	}
}
