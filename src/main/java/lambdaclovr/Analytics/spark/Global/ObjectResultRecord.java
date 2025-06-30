package Global;

import java.util.ArrayList;
import java.util.List;

public class ObjectResultRecord {
    public final String PredictedClass;
    public double HighestConfidence;
    public List<ObjectFrame> Frames;

    public ObjectResultRecord(String predictedClass) {
        HighestConfidence = 0.0;
        PredictedClass = predictedClass;
        Frames = new ArrayList<>();
    }

    public void findHighestConfidence() {
        Frames.forEach(f -> {
            if (f.Confidence > HighestConfidence)
                HighestConfidence = f.Confidence;
        });
    }
}
