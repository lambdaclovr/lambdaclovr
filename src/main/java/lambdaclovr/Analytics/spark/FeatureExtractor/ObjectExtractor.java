package FeatureExtractor;

import Global.*;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.transform.ColorConversionTransform;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.layers.objdetect.DetectedObject;
import org.deeplearning4j.nn.layers.objdetect.Yolo2OutputLayer;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectExtractor {
    ComputationGraph yoloModel;

    private HashMap<Integer, String> classes;

    private static final ObjectExtractor INSTANCE = new ObjectExtractor();

    private int inputWidth = 416;
    private int inputHeight = 416;
    private int inputChannels = 3;
    private int gridW = 13;
    private int gridH = 13;

    private NativeImageLoader nativeImageLoader;

    private final String[] modelClasses = { "person", "bicycle", "car", "motorbike", "aeroplane", "bus", "train",
            "truck", "boat", "traffic light", "fire hydrant", "stop sign", "parking meter", "bench", "bird", "cat",
            "dog", "horse", "sheep", "cow", "elephant", "bear", "zebra", "giraffe", "backpack", "umbrella", "handbag",
            "tie", "suitcase", "frisbee", "skis", "snowboard", "sports ball", "kite", "baseball bat", "baseball glove",
            "skateboard", "surfboard", "tennis racket", "bottle", "wine glass", "cup", "fork", "knife", "spoon", "bowl",
            "banana", "apple", "sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza", "donut", "cake", "chair",
            "sofa", "pottedplant", "bed", "diningtable", "toilet", "tvmonitor", "laptop", "mouse", "remote", "keyboard",
            "cell phone", "microwave", "oven", "toaster", "sink", "refrigerator", "book", "clock", "vase", "scissors",
            "teddy bear", "hair drier", "toothbrush" };

    public ObjectExtractor() {}

    public static ObjectExtractor getInstance() {
        return INSTANCE;
    }

    public void init() {
        System.out.println(">>> Loading Yolo2 CNN Model from local");

        try {
            long startTime = System.nanoTime();

            yoloModel = ModelSerializer.restoreComputationGraph(new File(Globals.Yolo2ModelPath));

            nativeImageLoader = new NativeImageLoader(inputWidth, inputHeight, inputChannels, new ColorConversionTransform(4)); // COLOR_BGR2RGB

            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(">>> Model Loaded Successfully");
            System.out.println(">>> Time taken in Loading Model: " + Utils.formatNanoSeconds(totalTime));

            System.gc();
        }
        catch (IOException e) {
            System.out.println(">>> Local Model Loading Error");
            e.printStackTrace();
        }
    }

    public String status() {
        return yoloModel != null ? "Live" : "Offline";
    }

    private List<DetectedObject> extractObjects(VideoFrame frame, double threshold) {
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
        org.bytedeco.opencv.opencv_core.Mat cvMat = converterToMat.convert(frame.Frame);

        try {
            INDArray image = nativeImageLoader.asMatrix(cvMat);

            if (image == null)
                System.out.println("ERROR: INDArray Image is null" + frame.toString());
            else {
                ImagePreProcessingScaler scaler = new ImagePreProcessingScaler(0, 1);
                scaler.transform(image);

                INDArray output = yoloModel.outputSingle(image);
                Yolo2OutputLayer outputLayer = (Yolo2OutputLayer) yoloModel.getOutputLayer(0);

                return outputLayer.getPredictedObjects(output, threshold);
            }
        }
        catch (IOException e) {
            System.out.println(">>> Object Extraction Error");
            e.printStackTrace();
        }

        return null;
    }

    public List<ObjectResultRecord> extractObjects(List<VideoFrame> frames, double threshold) {
        List<ObjectResultRecord> detectedObjects = new ArrayList<>();

        frames.forEach(frame -> {
            List<DetectedObject> objects = extractObjects(frame, threshold);

            objects.forEach(obj -> {
                String predictedClass = modelClasses[obj.getPredictedClass()];

                boolean exists = detectedObjects.stream().anyMatch(o -> o.PredictedClass.equals(predictedClass));

                // If this object does not exist int the Objects List, add it
                if (!exists) {
                    ObjectResultRecord objectRecord = new ObjectResultRecord(predictedClass);

                    objectRecord.Frames.add(new ObjectFrame(frame.FrameNumber, frame.Frame.timestamp, obj.getConfidence()));

                    detectedObjects.add(objectRecord);
                }
                else {
                    ObjectResultRecord ob = detectedObjects.stream().filter(o -> o.PredictedClass.equals(predictedClass)).findFirst().orElse(null);

                    if (ob != null) {
                        exists = ob.Frames.stream().anyMatch(o -> o.FrameNumber == frame.FrameNumber);

                        if (!exists) {
                            ob.Frames.add(new ObjectFrame(frame.FrameNumber, frame.Frame.timestamp, obj.getConfidence()));
                        }
                    }
                }
            });
        });

        detectedObjects.forEach(o -> o.findHighestConfidence());

        System.gc();

        return detectedObjects;
    }

    public List<ObjectResultRecord> extractObjects(String filename, double threshold) {
        List<ObjectResultRecord> detectedObjects = new ArrayList<>();

        List<VideoFrame> frames = Utils.extractKeyFrames(filename);

        return extractObjects(frames, threshold);
    }
}
