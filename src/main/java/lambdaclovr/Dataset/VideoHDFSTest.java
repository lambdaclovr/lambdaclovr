import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.log4j.Logger;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class VideoHDFS {
    final static Logger logger = Logger.getLogger(VideoHDFSTest.class);

    public static void main(String[] args) throws Exception {
        InputStream is = getVideoFromHDFS("hdfs/path");
        frameExtractionHDFS(is);
    }

    private static FileSystem getFileSystem() throws IOException {
        String hdfsuri = "host:port";

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsuri);
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        System.setProperty("HADOOP_USER_NAME", "hdfs");
        FileSystem fs = FileSystem.get(URI.create(hdfsuri), conf);

        return fs;
    }

    private static InputStream getVideoFromHDFS(String path) throws IOException {
        FileSystem fs = getFileSystem();

        boolean exists = fs.exists(new org.apache.hadoop.fs.Path(path));

        if (!exists) return null;

        logger.info(">> Path Exists: " + exists);

        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("hdfs:///siat/models/"), true);
        while (files.hasNext()) {
            LocatedFileStatus file = files.next();
            System.out.println(file.getPath());
        }

        return new BufferedInputStream(fs.open(new org.apache.hadoop.fs.Path(path)));
    }

    private static void frameExtractionHDFS(InputStream inputStream) throws FrameGrabber.Exception {
        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET);

        Java2DFrameConverter converter = new Java2DFrameConverter();

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(inputStream);
        frameGrabber.start();

        int numFrames = frameGrabber.getLengthInFrames();
        double frameRate = frameGrabber.getFrameRate();
        long vidoLength = frameGrabber.getLengthInTime();
        System.out.println("FFMPEG Info: Video has length " + formatMicroSeconds(vidoLength) + ", " + numFrames + " frames and has frame rate of " + frameRate + " fps");

        int frameCount = 0;
        int keyFrameCount = 0;
        for(int i = 0; i <= numFrames; i++) {
            org.bytedeco.javacv.Frame frame = frameGrabber.grabImage();

            if (frame != null) {
                long frameNumber = frameGrabber.getFrameNumber();
                long timeStamp = frameGrabber.getTimestamp();

                frameCount++;

                if (frame.keyFrame) {
                    keyFrameCount++;
                }
            }
        }
        System.out.println("After Extraction: Video has " + frameCount + " frames, and has " + keyFrameCount + " keyframe(s)");

        frameGrabber.stop();
    }
}
