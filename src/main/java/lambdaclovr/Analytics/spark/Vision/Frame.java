package org.spark.vision;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.nativeblas.Nd4jCuda;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Frame {
    private long FrameNumber;

    private String videoID;

    private org.bytedeco.javacv.Frame frame;

    public Frame() {

    }

    public Frame(org.bytedeco.javacv.Frame frame, long frameNumber, String videoID) {
        this.frame = frame;
        this.FrameNumber = frameNumber;
        this.videoID = videoID;
    }

    public org.bytedeco.javacv.Frame getFrame() {
        return frame;
    }

    public boolean IsKeyFrame() {
        return frame.keyFrame;
    }

    public long getFrameNumber() {
        return FrameNumber;
    }

    public long getTimeStampLong() {
        return frame.timestamp;
    }

    public String getTimeStamp() {
        long milliseconds   = (frame.timestamp / 1000) % 1000;
        long seconds    = (((frame.timestamp / 1000) - milliseconds) / 1000) % 60;
        long minutes    = (((((frame.timestamp / 1000) - milliseconds) / 1000) - seconds) / 60) % 60;
        long hours      = ((((((frame.timestamp / 1000) - milliseconds) / 1000) - seconds) / 60) - minutes) / 60;

        return hours + ":" + minutes + ":" + seconds;
    }

    public String getVideoID() {
        return videoID;
    }

    public BufferedImage getBufferedImage() {
        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET); // or AV_LOG_PANIC

        Java2DFrameConverter converter = new Java2DFrameConverter();

        return converter.getBufferedImage(frame);
    }

    public org.bytedeco.opencv.opencv_core.Mat getMat() {
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

        org.bytedeco.opencv.opencv_core.Mat cvMat = converterToMat.convert(frame);

        return cvMat;
    }

    private String getFileNameWithoutExtension(String path) {
        return new File(path).getName().replaceFirst("[.][^.]+$", "");
    }

    public void save(String outDir) {
        try {
            String path = outDir + "/" + getFileNameWithoutExtension(videoID) + "__" + FrameNumber + ".png";
            File file = new File(path);

            BufferedImage image = getBufferedImage();

            if (image != null)
                ImageIO.write(image, "png", file);
            else
                System.out.println("Image is null: " + path);

        } catch (IOException e) {}
    }

    public void saveMat(String outDir) {
        String path = outDir + "/" + getFileNameWithoutExtension(videoID) + "__" + FrameNumber + ".png";
        org.bytedeco.opencv.global.opencv_imgcodecs.imwrite(path, getMat());
    }

    public INDArray extractFeatures()
    {
        INDArray fv = SPFExtractor.getInstance().extractSpatialFeaturesUsingGPU(getMat());

        return fv;
    }    
}

class FeatureVector implements Serializable {
    private String videoID;
    private long frameNumber;
    private long timeStamp;
    private INDArray featureVector;

    public FeatureVector(String videoID, long frameNumber, long timeStamp, INDArray featureVector) {
        this.videoID = videoID;
        this.frameNumber = frameNumber;
        this.timeStamp = timeStamp;
    }
}
