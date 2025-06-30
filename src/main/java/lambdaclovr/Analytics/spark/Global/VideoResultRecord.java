package Global;

public class VideoResultRecord {
    public final String VideoID;
    public final double Similarity;
    public final int FrameNumber;
    public final long TimeStamp;

    public VideoResultRecord(String videoID, double similarity) {
        VideoID = videoID;
        Similarity = similarity;
    }

    @Override
    public String toString() {
        return VideoID + "," + Similarity;
    }
}
