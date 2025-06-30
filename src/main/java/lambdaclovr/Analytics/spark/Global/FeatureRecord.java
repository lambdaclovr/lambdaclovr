package Global;

import org.nd4j.linalg.cpu.nativecpu.NDArray;

public class FeatureRecord {
    public final String VideoID;
    public final int FrameNumber;
    public final long TimeStamp;
    public final NDArray FeatureVector;

    public FeatureRecord(String videoID, int frameNumber, long timeStamp, NDArray featureVector) {
        VideoID = videoID;
        FrameNumber = frameNumber;
        TimeStamp = timeStamp;
        FeatureVector = featureVector;
    }
}
