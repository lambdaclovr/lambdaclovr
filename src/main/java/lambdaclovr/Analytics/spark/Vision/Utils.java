package org.spark.vision;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public final class Utils {
    Utils() {}

    public static Mat img2Mat(BufferedImage in) {
        Mat out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
        byte[] data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
        int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());

        for (int i = 0; i < dataBuff.length; i++) {
            data[i * 3] = (byte) ((dataBuff[i]));
            data[i * 3 + 1] = (byte) ((dataBuff[i]));
            data[i * 3 + 2] = (byte) ((dataBuff[i]));
        }

        out.put(0, 0, data);

        return out;
    }

    public static BufferedImage mat2Img(Mat matrix) {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elemSize = (int)matrix.elemSize();
        byte[] data = new byte[cols * rows * elemSize];
        int type = 0;

        matrix.get(0, 0, data);

        switch (matrix.channels()) {
            case 1:
                type = BufferedImage.TYPE_BYTE_GRAY;
                break;
            case 3:
                type = BufferedImage.TYPE_3BYTE_BGR;
                // bgr to rgb
                byte b;
                for(int i=0; i<data.length; i=i+3) {
                    b = data[i];
                    data[i] = data[i+2];
                    data[i+2] = b;
                }
                break;
            default:
                
        }

        BufferedImage image2 = new BufferedImage(cols, rows, type);

        image2.getRaster().setDataElements(0, 0, cols, rows, data);

        return image2;
    }


    public static Mat mat2gray(Mat image) {
        Mat grayImage = new Mat();

        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_RGB2GRAY);

        return grayImage;
    }

    public static void histEqualize(Mat mat) {
        
    }

    public static void histEqualize(BufferedImage image) {
        
    }



    public static void enhanceImageContrast(Mat mat) {
        
    }

    public static void enhanceImageContrast(BufferedImage image) {
        
    }



    public static void smoothImage(Mat mat) {
        
    }

    public static void smoothImage(BufferedImage image) {
        
    }



    public static void sharpenImage(Mat mat) {
        
    }

    public static void sharpenImage(BufferedImage image) {
        
    }



    public static void enhanceImageBrightness(Mat mat) {
        
    }

    public static void enhanceImageBrightness(BufferedImage image) {
        
    }



    public static void resizeImage(Mat mat, int width, int height) {
        
    }

    public static void resizeImage(BufferedImage image, int width, int height) {
        
    }



    public static void deNoiseImage(Mat mat) {
        
    }

    public static void deNoiseImage(BufferedImage image) {
        
    }



    public static float detectMotionBlur(Mat mat) {
        return 0;
    }

    public static float detectMotionBlur(BufferedImage image) {
        return 0;
    }



    public static void motionDeBlur(Mat mat) {
        
    }

    public static void motionDeBlur(BufferedImage image) {
        
    }



    public static void convertVideoToMP4(String input, String output) {

    }

    public static InputStream cloneInputStream(final InputStream inputStream) {
        try {
            inputStream.mark(0);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int readLength = 0;
            while ((readLength = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLength);
            }
            inputStream.reset();
            outputStream.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$-" + length + "s", string);
    }

    public static String fixedLengthString(String string, int length, Boolean rightAligned) {
        if (rightAligned)
            return String.format("%1$-" + length + "s", string);

        return String.format("%1$" + length + "s", string);
    }
}
