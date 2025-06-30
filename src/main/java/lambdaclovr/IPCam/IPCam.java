import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class IPCam {
    public BufferedImage frame;
    public String camURL = ""
    
    private void Start() {
        isRunning = true;
        videoCaptureThread = new Thread(IPCamFrameCapture);
        videoCaptureThread.start();
        btnCapture.setText("Stop");
    }

    private void Stop() {
        isRunning = false;
        setTitle("App");
        videoCaptureThread.interrupt();
        btnCapture.setText("Start");
    }

    Runnable IPCamFrameCapture = () -> {
        int fpsCount = 0;
        int fpsAvgCount = 0;
        double fpsTotal = 0;
        Instant fpsStartTime = null;
        DecimalFormat df = new DecimalFormat("##.##");

        try {
            boolean frameResized = false;

            org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET);

            Java2DFrameConverter converter = new Java2DFrameConverter();

            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(camURL);

            frameGrabber.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            frameGrabber.setOption("rtsp_transport", "tcp");
            frameGrabber.setFrameRate(60);
            frameGrabber.setImageWidth(1280);
            frameGrabber.setImageHeight(720);

            frameGrabber.start();

            while (!Thread.currentThread().isInterrupted()) {
                org.bytedeco.javacv.Frame frame = frameGrabber.grabFrame();

                if (frame != null) {
                    if (++fpsCount == 1) { fpsStartTime = Instant.now(); }
                    if (fpsCount == 60) {
                        fpsAvgCount++;
                        double fps = (fpsCount * 1000.0) / Duration.between(fpsStartTime, Instant.now()).toMillis();
                        fpsTotal += fps;
                        setTitle("Frame Rate: " + df.format(fps) + " fps" + " [" + df.format(fpsTotal / fpsAvgCount) + " avg]");
                        fpsCount = 0;
                    }

                    BufferedImage image = converter.getBufferedImage(frame);
                    
                    if (image != null) {
                    
                        if (!frameResized) {
                            resizeUIWindowFrame(image.getWidth(), image.getHeight());
                            frameResized = true;
                        }

                        frame = image;
                    }
                }
            }

            frameGrabber.stop();

            lblImage.setIcon(null);
            lblImage.setBackground(Color.BLACK);
        }
        catch (Exception e) {
            e.printStackTrace();            
            Stop();
            resizeUIWindowFrame(initialVideoFrameWidth, initialVideoFrameHeight);
        }
    };    
}