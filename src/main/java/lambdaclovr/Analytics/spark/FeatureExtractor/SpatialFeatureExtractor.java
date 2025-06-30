package FeatureExtractor;

import Global.Globals;
import Global.VideoFrame;
import Global.Utils;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.VGG16ImagePreProcessor;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpatialFeatureExtractor {
    ComputationGraph vggTransferLearning;

    private static final SpatialFeatureExtractor INSTANCE = new SpatialFeatureExtractor();

    public SpatialFeatureExtractor() {}

    public static SpatialFeatureExtractor getInstance() {
        return INSTANCE;
    }

    public void init() {
        System.out.println(">>> Loading VGG19 CNN Model from local");

        try {
            long startTime = System.nanoTime();

            vggTransferLearning = ModelSerializer.restoreComputationGraph(new File(Globals.VGG19ModelPath));

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
        return vggTransferLearning != null ? "Live" : "Offline";
    }

    private INDArray extractFeatures(VideoFrame frame) {
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
        org.bytedeco.opencv.opencv_core.Mat cvMat = converterToMat.convert(frame.Frame);

        try {
            NativeImageLoader loader = new NativeImageLoader(224, 224, 3);

            INDArray image = loader.asMatrix(cvMat);

            if (image == null)
                System.out.println("ERROR: INDArray Image is null" + frame.toString());
            else {
                DataNormalization scalar = new VGG16ImagePreProcessor();

                scalar.transform(image);

                Map<String, INDArray> stringINDArrayMap = vggTransferLearning.feedForward(image, false);

                INDArray featureVector = stringINDArrayMap.get("predictions"); //fc2,predictions

                return featureVector;

                saveFeature(frame, featureVector);
            }
        }
        catch (IOException e) {
            System.out.println(">>> Spatial Feature Extraction Error");
            e.printStackTrace();
        }

        return null;
    }

    public List<NDArray> extractFeatures(List<VideoFrame> frames) {
        List<NDArray> features = new ArrayList<>();

        frames.forEach(frame -> {
            INDArray featureVector = extractFeatures(frame);
            features.add((NDArray) Nd4j.create(featureVector.data().asDouble()));
        });

        System.gc();

        return features;
    }

    public List<NDArray> extractFeatures(String filename) {
        List<NDArray> features = new ArrayList<>();

        List<VideoFrame> frames = Utils.extractKeyFrames(filename);

        return extractFeatures(frames);
    }
}
