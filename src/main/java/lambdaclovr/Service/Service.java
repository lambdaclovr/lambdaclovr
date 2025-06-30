import Response.StandardResponse;
import Response.StatusResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.utils.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import static spark.Spark.*;

public class Service {
    public static void main(String[] args) {
        get("/query", (request, response) -> {
            response.type("application/json");

            String paramThreshold = request.queryParams("threshold");
            String paramVideoID = request.queryParams("video");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("VideoID", paramVideoID);
            jsonObject.addProperty("Threshold", Double.parseDouble(paramThreshold));

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonObject));
        });

        get("/query/:threshold/:video", (request, response) -> {
            response.type("application/json");

            String paramThreshold = request.params(":threshold");
            String paramVideoID = request.params(":video");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("VideoID", paramVideoID);
            jsonObject.addProperty("Threshold", Double.parseDouble(paramThreshold));

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonObject));
        });
        
        post("/image", ServiceTest::imageHandler);        
    }

    private static Object multipartHandle(Request request, Response response) {
        try {
            response.type("application/json");

            if (request.raw().getAttribute("org.eclipse.jetty.multipartConfig") == null) {
                MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
            }
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("%Temp%"));

            Collection<Part> parts = request.raw().getParts();
            System.out.println("Size: " + parts.size());
            System.out.println("Parts: " + parts.toString());

            Part name = request.raw().getPart("name");
            Part age = request.raw().getPart("age");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Name", IOUtils.toString(name.getInputStream()));
            jsonObject.addProperty("Age", IOUtils.toString(age.getInputStream()));

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonObject));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object imageHandler(Request request, Response response) {
        try {
            response.type("application/json");

            if (request.raw().getAttribute("org.eclipse.jetty.multipartConfig") == null) {
                MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
            }

            Collection<Part> parts = request.raw().getParts();
            for (Part part : parts) {
                System.out.println("Name: " + part.getName());
                System.out.println("Size: " + part.getSize());
                System.out.println("Filename: " + part.getSubmittedFileName());
                System.out.println();
            }

            Part image = request.raw().getPart("image");
            Part threshold = request.raw().getPart("threshold");

            String fName = image.getSubmittedFileName();

            String resourceDirectory = new File(Paths.get("src","main","resources").toUri()).getAbsolutePath();

            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            System.out.println("Image Width: " + bufferedImage.getWidth() + " Image Height: " + bufferedImage.getHeight());
            ImageIO.write(bufferedImage, "jpg", new File(Paths.get(resourceDirectory, "uploaded_bufferedImage.jpg").toUri()));

            Path out = Paths.get(resourceDirectory, "uploaded_" + fName);
            try (final InputStream in = image.getInputStream()) {
                Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
                image.delete();
            } catch (Exception ignored) {}

            multipartConfigElement = null;
            parts = null;
            image = null;

            JsonObject result = new JsonObject();
            result.addProperty("Status", "SUCCESS");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Name", fName);
            jsonObject.addProperty("Threshold", Double.parseDouble(IOUtils.toString(threshold.getInputStream())));

            result.add("data", jsonObject);

            return new Gson().toJson(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}