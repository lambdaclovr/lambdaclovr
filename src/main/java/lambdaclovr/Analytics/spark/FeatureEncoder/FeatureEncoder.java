import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FeatureEncoder {
    private static String encodeFeatures(double[] featureVector) {
        String t = "f";
        String result = "";
        double mQ = Math.E * Math.sqrt(1000);

        int[] w = new int[featureVector.length];

        for (int i = 0; i < featureVector.length; i++) {
            w[i] = (int)Math.floor(mQ * featureVector[i]);        
        }

        for (int i = 0; i < w.length; i++) {
            if (w[i] != 0) {
                for (int j = 0; j < w[i]; j++) {
                    result += Integer.toHexString(i + 1) + " ";
                }
            }
        }

        return result.trim();
    }    
}
