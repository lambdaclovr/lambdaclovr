package org.spark.vision;

import org.apache.spark.api.java.JavaRDD;
import org.apache.tika.sax.BodyContentHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp4.MP4Parser;
import org.bytedeco.javacv.FFmpegFrameGrabber;

public class Video {
    private VideoMetadata Metadata;

    private String fileName;
    private InputStream videoStream;

    public Video()
    {
        fileName = "";

        Metadata = null;
    }

    public Video(InputStream videoStream, String fileName, boolean withMetadata)
    {
        this();

        this.videoStream = videoStream;
        this.fileName = fileName;

        if (withMetadata) {
            getMetadata();
        }
    }

    public void getMetadata() {
        try {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext pContext = new ParseContext();

            MP4Parser MP4Parser = new MP4Parser();
            MP4Parser.parse(videoStream, handler, metadata, pContext);

            videoStream.reset();

            String[] metadataNames = metadata.names();

            int maxKeyLength = 0;
            for (String name : metadataNames)
                if (maxKeyLength < name.length())
                    maxKeyLength = name.length();
            maxKeyLength++;

            for (String name : metadataNames) {
                System.out.println(Utils.fixedLengthString(name, maxKeyLength) + ": " + metadata.get(name));
            }            
        }
        catch (Exception e) {}
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedImage getThumbnail() {
        return null;
    }

    public Iterator<Frame> split(FrameOptions frameOptions, ImageOptions imageOptions) {
        List<Frame> frames = new ArrayList<Frame>();

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoStream);

        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET); // or AV_LOG_PANIC

        try {
            frameGrabber.start();

            int numFrames = frameGrabber.getLengthInFrames();
    
            int frameCount = 0;
            int keyFrameCount = 0;
            for(int i = 0; i <= numFrames; i++) {
                org.bytedeco.javacv.Frame frame = frameGrabber.grabImage();

                if (frame != null) {
                    long frameNumber = frameGrabber.getFrameNumber();
                    long timeStamp = frameGrabber.getTimestamp();

                    frameCount++;

                    if (frame.keyFrame) {
                        keyFrameCount++;

                        frames.add(new Frame(frame.clone(), frameNumber, fileName));
                    }
                }
            }
    
            frameGrabber.stop();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return frames.iterator();
    }

    public int split() {
        return split(FrameOptions.ALL);
    }

    public int split(FrameOptions frameOptions) {
        return 0;
    }

    public int split(FrameOptions frameOptions, int step) {
        return 0;
    }

    public int split(FrameOptions frameOptions, LocalTime timeStamp) {
        return 0;
    }

    public int split(FrameOptions frameOptions, int fromFrame, int toFrame) {
        return 0;
    }

    public JavaRDD<Frame> split(FrameOptions frameOptions, LocalTime fromTime, LocalTime toTime) {
        return null;
    }
}
