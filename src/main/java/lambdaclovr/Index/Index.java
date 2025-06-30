package Index;

import Global.FeatureRecord;
import Global.Globals;
import Global.VideoResultRecord;
import Global.Utils;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Index {
    private double thresholdValue;

    private List<FeatureRecord> index;
    private HashMap<String, Integer> classes;

    public Index() {
        thresholdValue = 0.5;

        index = new ArrayList<>();
        classes = new HashMap<>();
    }

    public void load() {
        try {
            File indexedFeatureFile = new File(Globals.FeatureVectorsPath);

            long startTime = System.nanoTime();

            Scanner scanner = new Scanner(indexedFeatureFile);
            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                List<String> tokenList = Arrays.asList(line.split(","));

                String videoID = tokenList.get(0);
                int frameNumber = Integer.parseInt(tokenList.get(1));
                long timeStamp = Long.parseLong(tokenList.get(2));

                String[] feature = tokenList.get(tokenList.size() - 1).split(("\\s+"));

                double[] featureVector = new double[feature.length];
                for (int i = 0; i < feature.length; i++)
                    featureVector[i] = Double.parseDouble(feature[i]);

                index.add(new FeatureRecord(videoID, frameNumber, timeStamp, (NDArray) Nd4j.create(featureVector)));

                String className = Utils.getClassName(videoID);

                if (classes.containsKey(className))
                    classes.put(className, classes.get(className) + 1);
                else
                    classes.put(className, 1);
            }

            scanner.close();

            System.gc();

            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

        }
        catch (IOException ignored) {}
    }

    public FeatureRecord get(int pos) {
        return index.get(pos);
    }

    public int count() {
        return index.size();
    }

    public List<String> query(FeatureRecord queryFeatureVector) {
        return query(queryFeatureVector, thresholdValue);
    }

    public List<String> query(FeatureRecord queryFeatureVector, double threshold) {
        List<String> matches = new ArrayList<>();

        long startTime = System.nanoTime();

        index.forEach(indexRecord -> {
            double similarity = cosineSimilarity(queryFeatureVector.FeatureVector, indexRecord.FeatureVector);

            if (similarity >= threshold) {
                if (!matches.contains(indexRecord.VideoID)) {
                    matches.add(indexRecord.VideoID + "," + similarity);
                }
            }
        });

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.gc();

        return matches;
    }

    public List<VideoResultRecord> query(List<NDArray> queryFeatureVectors) {
        return query(queryFeatureVectors, thresholdValue);
    }

    public List<VideoResultRecord> query(List<NDArray> queryFeatureVectors, double threshold) {
        List<VideoResultRecord> matches = new ArrayList<>();

        long startTime = System.nanoTime();

        index.forEach(indexRecord -> {
            double highestSimilarity = 0.0;
            String videoID = null;

            for (int i = 0; i < queryFeatureVectors.size(); i++) {
                double similarity = cosineSimilarity(queryFeatureVectors.get(i), indexRecord.FeatureVector);

                if (similarity > highestSimilarity) {
                    highestSimilarity = similarity;
                    videoID = indexRecord.VideoID;
                }
            }

            if (highestSimilarity >= threshold) {
                if (!matches.contains(videoID)) {
                    matches.add(new VideoResultRecord(videoID, highestSimilarity));
                }
            }
        });

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.gc();

        return matches;
    }

    public int getClassCount() {
        return classes.size();
    }

    public List<String> getClassNames() {
        List<String> classNames = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: classes.entrySet()) {
            classNames.add(entry.getKey());
        }

        return classNames;
    }

    public HashMap<String, Integer> getClasses() {
        return classes;
    }

    private double cosineSimilarity(final NDArray source, final NDArray destination) {
        return Transforms.cosineSim(source, destination);
    }
}