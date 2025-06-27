package lambdaclovr.dsl.data.acquisition.streaming.operations.client.examples;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class OpenCVTest {

	public static void main(String[] args) throws Exception {


		// nu.pattern.OpenCV.loadShared();
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		 Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
	      System.out.println( "mat = " + mat.dump() );
		 
		 
		 System.out.printf("java.library.path: %s%n",
		 System.getProperty("java.library.path"));
		 System.loadLibrary("opencv_java320");
		
		 // Camera object and testing camera
		
		 Mat frame = new Mat();
		 
		 String URL = "F:/Ubuntu/backup/siyamul/Videos/f.mp4";
		 //String URL = "f.mp4";
		 System.out.println(URL);
		
		 VideoCapture camera = new VideoCapture();
		 camera.open(URL);
		
		 System.out.println(camera.isOpened());
		 
		 
		 if (camera.isOpened()) {	 
			 System.out.println(camera.isOpened());
		 }
	}
}
