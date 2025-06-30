import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.spark.vision.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.bytedeco.ffmpeg.global.avcodec.*;

public class Metadata {
    public static void main(String[] args) throws IOException {
        org.bytedeco.ffmpeg.global.avutil.av_log_set_level(org.bytedeco.ffmpeg.global.avutil.AV_LOG_QUIET);

        String video = "cam/or/video/path";
        
        InputStream stream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(video)));

        getIndividualMetadata(video);
        
        getIndividualMetadataStream(stream);
    }


    private static void getIndividualMetadata(final String file) throws FrameGrabber.Exception {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);

        frameGrabber.start();

        Map<String, String> metadata = new HashMap<>();
        
        BytePointer videoCodec = avcodec_get_name(frameGrabber.getVideoCodec());
        System.out.println("\nVideo Codec Name: " + videoCodec.getString());
        System.out.println("Audio Codec Name: " + avcodec_get_name(frameGrabber.getAudioCodec()).getString());

        if (frameGrabber.hasVideo()) {
            metadata.put("format",                                 frameGrabber.getFormat());
            metadata.put("width",                   String.valueOf(frameGrabber.getImageWidth()));
            metadata.put("height",                  String.valueOf(frameGrabber.getImageHeight()));
            metadata.put("pixel_format",            String.valueOf(frameGrabber.getPixelFormat()));
            metadata.put("gamma",                   String.valueOf(frameGrabber.getGamma()));
            metadata.put("video_codec",             String.valueOf(frameGrabber.getVideoCodec()));
            metadata.put("video_codec_name",                       frameGrabber.getVideoCodecName());
            metadata.put("video_bitrate",           String.valueOf(frameGrabber.getVideoBitrate()));
            metadata.put("aspect_ratio",            String.valueOf(frameGrabber.getAspectRatio()));
            metadata.put("framerate",               String.valueOf(frameGrabber.getFrameRate()));
            metadata.put("video_framerate",         String.valueOf(frameGrabber.getVideoFrameRate()));
            metadata.put("length_in_frames",        String.valueOf(frameGrabber.getLengthInFrames()));
            metadata.put("length_in_video_frames",  String.valueOf(frameGrabber.getLengthInVideoFrames()));
            metadata.put("length_in_time",          String.valueOf(frameGrabber.getLengthInTime()));
            metadata.put("bits_per_pixel",          String.valueOf(frameGrabber.getBitsPerPixel()));
        }

        if (frameGrabber.hasAudio()) {
            metadata.put("audio_channels",          String.valueOf(frameGrabber.getAudioChannels()));
            metadata.put("audio_framerate",         String.valueOf(frameGrabber.getAudioFrameRate()));
            metadata.put("audio_codec",             String.valueOf(frameGrabber.getAudioCodec()));
            metadata.put("audio_codec_name",        String.valueOf(frameGrabber.getAudioCodecName()));
            metadata.put("audio_bitrate",           String.valueOf(frameGrabber.getAudioBitrate()));
            metadata.put("audio_sample_format",     String.valueOf(frameGrabber.getSampleFormat()));
            metadata.put("audio_sample_rate",       String.valueOf(frameGrabber.getSampleRate()));
            metadata.put("length_in_audio_frames",  String.valueOf(frameGrabber.getLengthInAudioFrames()));
        }

        frameGrabber.stop();
    }


    private static void getIndividualMetadataStream(final InputStream stream) throws FrameGrabber.Exception {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(stream);

        frameGrabber.start();

        Map<String, String> metadata = new HashMap<>();
        
        BytePointer videoCodec = avcodec_get_name(frameGrabber.getVideoCodec());
        System.out.println("\nVideo Codec Name: " + videoCodec.getString());
        System.out.println("Audio Codec Name: " + avcodec_get_name(frameGrabber.getAudioCodec()).getString());

        if (frameGrabber.hasVideo()) {
            metadata.put("format",                                 frameGrabber.getFormat());
            metadata.put("width",                   String.valueOf(frameGrabber.getImageWidth()));
            metadata.put("height",                  String.valueOf(frameGrabber.getImageHeight()));
            metadata.put("pixel_format",            String.valueOf(frameGrabber.getPixelFormat()));
            metadata.put("gamma",                   String.valueOf(frameGrabber.getGamma()));
            metadata.put("video_codec",             String.valueOf(frameGrabber.getVideoCodec()));
            metadata.put("video_codec_name",                       frameGrabber.getVideoCodecName());
            metadata.put("video_bitrate",           String.valueOf(frameGrabber.getVideoBitrate()));
            metadata.put("aspect_ratio",            String.valueOf(frameGrabber.getAspectRatio()));
            metadata.put("framerate",               String.valueOf(frameGrabber.getFrameRate()));
            metadata.put("video_framerate",         String.valueOf(frameGrabber.getVideoFrameRate()));
            metadata.put("length_in_frames",        String.valueOf(frameGrabber.getLengthInFrames())); 
            metadata.put("length_in_video_frames",  String.valueOf(frameGrabber.getLengthInVideoFrames()));
            metadata.put("length_in_time",          String.valueOf(frameGrabber.getLengthInTime()));
            metadata.put("bits_per_pixel",          String.valueOf(frameGrabber.getBitsPerPixel()));
        }

        if (frameGrabber.hasAudio()) {
            metadata.put("audio_channels",          String.valueOf(frameGrabber.getAudioChannels()));
            metadata.put("audio_framerate",         String.valueOf(frameGrabber.getAudioFrameRate()));
            metadata.put("audio_codec",             String.valueOf(frameGrabber.getAudioCodec()));
            metadata.put("audio_codec_name",        String.valueOf(frameGrabber.getAudioCodecName()));
            metadata.put("audio_bitrate",           String.valueOf(frameGrabber.getAudioBitrate()));
            metadata.put("audio_sample_format",     String.valueOf(frameGrabber.getSampleFormat()));
            metadata.put("audio_sample_rate",       String.valueOf(frameGrabber.getSampleRate()));
            metadata.put("length_in_audio_frames",  String.valueOf(frameGrabber.getLengthInAudioFrames()));
        }

        frameGrabber.stop();
    }
}
