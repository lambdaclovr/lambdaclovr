package Global;

import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String getClassName(String VideoID) {
        return VideoID.split("_")[1];
    }

    public static <T> T toFixedLengthString(T value) {
        return toFixedLengthString(value, 12);
    }

    public static <T> T toFixedLengthString(T value, int length) {
        return (T)String.format("%-" + length + "." + length + "s", value);
    }

    public static String round(double value) {
        return round(value, 0);
    }

    public static String round(double value, int decimalPlaces) {
        String pattern = "#";

        if (decimalPlaces > 0) {
            pattern = "." + org.apache.commons.lang3.StringUtils.repeat("#", decimalPlaces);
        }

        return new DecimalFormat(pattern).format(value);
    }

    public static String getFileNameWithoutExtension(String path) {
        return new File(path).getName().replaceFirst("[.][^.]+$", "");
    }

    public static String formatNanoSeconds(long nanos) {
        return String.format("%02d:%02d:%02d.%03d",
                TimeUnit.NANOSECONDS.toHours(nanos),
                TimeUnit.NANOSECONDS.toMinutes(nanos) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(nanos)),
                TimeUnit.NANOSECONDS.toSeconds(nanos) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(nanos)),
                TimeUnit.NANOSECONDS.toMillis(nanos) - TimeUnit.SECONDS.toMillis(TimeUnit.NANOSECONDS.toSeconds(nanos)));
    }

    public static String formatMilliSeconds(long millis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String getQueryVideoPath(String videoID) {
        return Globals.QueryBasePath + videoID;
    }

    public static List<VideoFrame> extractKeyFrames(String filename) {
        List<VideoFrame> frames = new ArrayList<>();

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(filename);

        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET); // or AV_LOG_PANIC

        try {
            frameGrabber.start();

            int numFrames = frameGrabber.getLengthInFrames();

            for(int i = 0; i <= numFrames; i++) {
                org.bytedeco.javacv.Frame frame = frameGrabber.grabImage();

                if (frame != null) {
                    int frameNumber = frameGrabber.getFrameNumber();

                    if (frame.keyFrame) {
                        frames.add(new VideoFrame(frame.clone(), frameNumber, Utils.getFileNameWithoutExtension(filename)));
                    }
                }
            }

            frameGrabber.stop();
        }
        catch (IOException e) {
            System.out.println(">>> VidoeFrame Extraction Exception:");
            e.printStackTrace();
        }

        return frames;
    }
}
