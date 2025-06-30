import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.Base64;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;

public class VideoEventGenerator implements Runnable {
    private static final Logger logger = Logger.getLogger(VideoEventGenerator.class);

    private String cameraId;
    private String url;
    private Producer<String, String> producer;
    private String topic;

    public VideoEventGenerator(String cameraId, String url, Producer<String, String> producer, String topic) {
        this.cameraId = cameraId;
        this.url = url;
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public void run() {
        logger.info("Processing cameraId " + cameraId + " with url " + url);
        try {
            generateEvent(cameraId,url,producer,topic);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateEvent(String cameraId,String url,Producer<String, String> producer, String topic) throws Exception{
        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET);

        Java2DFrameConverter converter = new Java2DFrameConverter();

        Gson gson = new Gson();

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(url);
        frameGrabber.start();

        int numFrames = frameGrabber.getLengthInFrames();

        for(int i = 0; i <= numFrames; i++) {
            Frame frame = frameGrabber.grabImage();
            
            if (frame != null) {
                BufferedImage image = converter.getBufferedImage(frame);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", bos);
                byte[] imageBytes = bos.toByteArray();
                String base64String = Base64.getEncoder().encodeToString(imageBytes);

                String timestamp = new Timestamp(System.currentTimeMillis()).toString();

                JsonObject obj = new JsonObject();

                obj.addProperty("cameraId",cameraId);
                obj.addProperty("timestamp", timestamp);
                obj.addProperty("width", image.getWidth());
                obj.addProperty("height", image.getHeight());
                obj.addProperty("type", "jpg");
                obj.addProperty("data", base64String);

                String json = gson.toJson(obj);

                producer.send(new ProducerRecord<>(topic, cameraId, json), new EventGeneratorCallback(cameraId));

                logger.info("Generated events for cameraId=" + cameraId + ", timestamp="+timestamp);
            }
        }

        frameGrabber.stop();
    }

    private static class EventGeneratorCallback implements Callback {
        private String camId;

        public EventGeneratorCallback(String camId) {
            super();
            this.camId = camId;
        }

        @Override
        public void onCompletion(RecordMetadata rm, Exception e) {
            if (rm != null) {
                logger.info("cameraId=" + camId + ", partition=" + rm.partition());
            }
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}