package org.spark.vision;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Image extends Frame {
    private BufferedImage bufferedImage;
    private String fileName;

    Image(InputStream imageStream, String filename) {
        this.fileName = filename;

        try {
            bufferedImage = ImageIO.read(imageStream);
        }
        catch (IOException e) {}
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public final org.bytedeco.opencv.opencv_core.Mat getMat() {
        OpenCVFrameConverter.ToMat cv = new OpenCVFrameConverter.ToMat();
        return cv.convertToMat(new Java2DFrameConverter().convert(bufferedImage));
    }

    public final String getFileName() {
        return fileName;
    }

    public final int getWidth() {
        return bufferedImage.getWidth();
    }

    public final int getHeight() {
        return bufferedImage.getHeight();
    }

    private String getFileNameWithoutExtension(String path) {
        return new File(path).getName().replaceFirst("[.][^.]+$", "");
    }

    public void save(String outDir) {
        try {
            String path = outDir + "/" + getFileNameWithoutExtension(fileName) + ".png";
            File file = new File(path);

            if (bufferedImage != null)
                ImageIO.write(bufferedImage, "png", file);
            else
                System.out.println("Image is null: " + path);

        } catch (IOException e) {}
    }

    public void saveMat(String outDir) {
        String path = outDir + "/" + getFileNameWithoutExtension(fileName) + ".png";
        org.bytedeco.opencv.global.opencv_imgcodecs.imwrite(path, getMat());
    }
}