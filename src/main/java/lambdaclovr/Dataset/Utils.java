import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
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
        return String.format("%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
    }

    public static void saveWithoutNewline(String file, String textToWrite) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file), true));

        writer.write(textToWrite);

        writer.close();
    }
}
