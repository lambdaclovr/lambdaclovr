package org.spark.vision;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.bytedeco.opencv.opencv_core.Mat;

import org.deeplearning4j.util.ModelSerializer;
import org.datavec.image.loader.NativeImageLoader;

import org.deeplearning4j.nn.graph.ComputationGraph;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.VGG16ImagePreProcessor;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;

public class SPFExtractor implements Serializable {
    ComputationGraph vggTransferLearning;

    public SPFExtractor() {
    }

    private static final SPFExtractor INSTANCE = new SPFExtractor();

    public static SPFExtractor getInstance() {
        return INSTANCE;
    }

    public void init() {
        try {
            vggTransferLearning = ModelSerializer.restoreComputationGraph(new File("D:/siat/models/Dl4j/vgg19/vgg19_dl4j_inference.zip"));
        }
        catch (IOException e) {
            System.out.println(">>> Local Model Loading Error");
            e.printStackTrace();
        }
    }

    public void init(JavaSparkContextExt sc) {
        String modelPath = "hdfs:///siat/models/vgg19/vgg19_dl4j_inference.zip";
    
        try {
            org.apache.hadoop.fs.FileSystem fileSystem = org.apache.hadoop.fs.FileSystem.get(sc.hadoopConfiguration());

            BufferedInputStream is = new BufferedInputStream(fileSystem.open(new org.apache.hadoop.fs.Path(modelPath)));
    
            vggTransferLearning = ModelSerializer.restoreComputationGraph(is);
        }
        catch(Exception e) {
            System.out.println(">>> HDFS Model Loading Error");
            e.printStackTrace();
        }
    }

    public NDArray extractSpatialFeatures(Mat mat) {
        try {
            NativeImageLoader loader = new NativeImageLoader(224, 224, 3);
    
            INDArray image = loader.asMatrix(mat);

            if (image == null)
                System.out.println("## INDArray Image is null");
            else {
                DataNormalization scalar = new VGG16ImagePreProcessor();
                scalar.transform(image);
                Map<String, INDArray> stringINDArrayMap = vggTransferLearning.feedForward(image, false);
                NDArray featureVector = (NDArray)stringINDArrayMap.get("predictions");
                return featureVector;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public INDArray extractSpatialFeaturesUsingGPU(Mat mat) {
        try {
            NativeImageLoader loader = new NativeImageLoader(224, 224, 3);
        
            INDArray image = loader.asMatrix(mat);

            if (image == null)
                System.out.println("## INDArray Image is null");
            else {
                DataNormalization scalar = new VGG16ImagePreProcessor();
                scalar.transform(image);
                Map<String, INDArray> stringINDArrayMap = vggTransferLearning.feedForward(image, false);
                INDArray featureVector = stringINDArrayMap.get("predictions"); //fc2,predictions
                return featureVector;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}