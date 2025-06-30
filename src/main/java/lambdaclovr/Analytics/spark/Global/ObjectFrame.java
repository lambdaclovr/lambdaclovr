package Global;

public class ObjectFrame {
    public final long FrameNumber;
    public final long TimeStamp;
    public final double Confidence;

    public ObjectFrame(long frameNumber, long timeStamp, double confidence) {
        FrameNumber = frameNumber;
        TimeStamp = timeStamp;
        Confidence = confidence;
    }
}
