package Global;

public class VideoFrame {
    public final int FrameNumber;

    public final String VideoID;

    public final org.bytedeco.javacv.Frame Frame;

    public VideoFrame(org.bytedeco.javacv.Frame frame, int frameNumber, String videoID) {
        this.Frame = frame;
        this.FrameNumber = frameNumber;
        this.VideoID = videoID;
    }

    @Override
    public String toString() {
        return VideoID + "," + FrameNumber + "," + Frame.timestamp;
    }
}
