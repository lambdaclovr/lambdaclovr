import FeatureExtractor.ObjectExtractor;
import FeatureExtractor.SpatialFeatureExtractor;
import Global.*;
import Index.Index;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static spark.Spark.get;

public class IndexService {
    public static void main(String[] args) {
        long indexStartTime = System.nanoTime();

        Index index = new Index();
        index.load();
        
        SpatialFeatureExtractor.getInstance().init();
        System.out.println();
        ObjectExtractor.getInstance().init();

        long indexEndTime   = System.nanoTime();
        long indexTotalTime = indexEndTime - indexStartTime;

        get("/querytest/:video", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new Response.StandardResponse(Response.StatusResponse.SUCCESS, new Gson().toJsonTree(features)));
        });

        get("/query", (request, response) -> {
            response.type("application/json");

            String paramThreshold = request.queryParams("threshold");
            String paramVideoID = request.queryParams("video");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("VideoID", paramVideoID);
            jsonObject.addProperty("Threshold", Double.parseDouble(paramThreshold));

            return new Gson().toJson(new Response.StandardResponse(Response.StatusResponse.SUCCESS, jsonObject));
        });

        get("/query/:threshold/:video", (request, response) -> {
            response.type("application/json");

            String paramFileName = request.params(":video");
            String paramThreshold = request.params(":threshold");

            String filename = Utils.getQueryVideoPath(paramFileName);

            double threshold = Double.parseDouble(paramThreshold);

            List<NDArray> features = SpatialFeatureExtractor.getInstance().extractFeatures(filename);

            return new Gson().toJson(new Response.StandardResponse(Response.StatusResponse.SUCCESS, new Gson().toJsonTree(result)));
        });

        get("/status", (request, response) -> {
            response.type("application/json");

            long startTime = System.nanoTime();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("VGG19ModelStatus", SpatialFeatureExtractor.getInstance().status());
            jsonObject.addProperty("YoloModelStatus", ObjectExtractor.getInstance().status());
            jsonObject.addProperty("IndexedFeatures", index.count());

            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(">>> Query Execution Time: " + Utils.formatNanoSeconds(totalTime));
            
            return jsonObject;
        });

        get("/query", (request, response) -> {
            response.type("application/json");

            String paramThreshold = request.queryParams("threshold");
            String paramFileName = request.queryParams("filename");

            String filename = Utils.getQueryVideoPath(paramFileName);

            final double threshold = Double.parseDouble(paramThreshold);

            final long startTime = System.nanoTime();

            final List<VideoFrame> frames = Utils.extractKeyFrames(filename);

            List<ObjectResultRecord> objects = ObjectExtractor.getInstance().extractObjects(frames, 0.85);

            List<NDArray> features = SpatialFeatureExtractor.getInstance().extractFeatures(frames);

            List<VideoResultRecord> videos = index.query(features, threshold);

            Result results = new Result();

            results.Objects = objects;
            results.Videos = videos;

            final long endTime   = System.nanoTime();
            final long totalTime = endTime - startTime;
            System.out.println(">>> Query Execution Time: " + Utils.formatNanoSeconds(totalTime));

            System.gc();

            return new Gson().toJson(new Response.StandardResponse(Response.StatusResponse.SUCCESS, TimeUnit.NANOSECONDS.toMillis(totalTime), new Gson().toJsonTree(results)));
        });
    }
}