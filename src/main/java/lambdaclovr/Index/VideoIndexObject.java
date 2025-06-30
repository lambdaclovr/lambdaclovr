import org.apache.solr.client.solrj.beans.Field;

import java.util.UUID;

public class VideoIndexObject {
    @Field public String id;
    @Field public String videoID;
    @Field public long frameNumber;
    @Field public long timeStamp;
    @Field public String features;

    public VideoIndexObject(String uuid, String videoID, long frameNumber, long timeStamp, String features) {
        this.id = uuid;
        this.videoID = videoID;
        this.frameNumber = frameNumber;
        this.timeStamp = timeStamp;
        this.features = features;
    }

    public VideoIndexObject() {}
}
