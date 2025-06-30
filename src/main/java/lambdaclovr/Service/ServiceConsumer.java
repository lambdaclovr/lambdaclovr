import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceConsumer {
    private static void simpleGetWithoutParams() {
        try {
            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpClient httpClient = HttpClientBuilder.create().build();

            URL url = new URL("http://host:port/hello");

            HttpGet httpGet = new HttpGet(url.toURI());

            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(">>> Request successful!");

                String data = EntityUtils.toString(response.getEntity());

                System.out.println("\n>>> Response:");
                System.out.println(data);
            }
            else {
                System.out.println(">>> Request Failed!");
                System.out.println("Reason: " + response.getStatusLine());
            }
        }
        catch (Exception e) {
            System.out.println("Response Error:");
            e.printStackTrace();
        }
    }

    private static void simpleGetWithParams() {
        try {
            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpGet httpGet = new HttpGet("url");
            
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(">>> Request successful!");

                System.out.println("\n>>> Response String:");
                String json = EntityUtils.toString(response.getEntity());
                System.out.println(json);

                System.out.println("\n>>> Response JSON:");
                JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(jsonObj));

                System.out.println("\nJSON:");
                JsonArray array = JsonParser.parseString(json).getAsJsonArray();
                System.out.println(gson.toJson(array));

                System.out.println("\nJSON Elements Iterate:");
                for (JsonElement elm : array) {
                    System.out.println(elm.getAsJsonObject().get("predictedClass").getAsString());
                }
                for (int i=0; i < array.size(); i++) {
                    System.out.println(array.get(i).getAsJsonObject().get("confidence").getAsDouble());
                }
            }
            else {
                System.out.println(">>> Request Failed!");
                System.out.println("Reason: " + response.getStatusLine());
            }
        }
        catch (Exception e) {
            System.out.println("Response Error:");
            e.printStackTrace();
        }
    }

    private static void SimplePostWithParams() {
        try {
            URL url = new URL("url");

            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpPost httpPost = new HttpPost(url.toURI());

            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("name", "Jon Doe"));
            urlParameters.add(new BasicNameValuePair("age", "21"));

            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(">>> Request successful!");

                System.out.println("\n>>> Response String:");
                String json = EntityUtils.toString(response.getEntity());
                System.out.println(json);

                System.out.println("\n>>> Response JSON:");
                JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(jsonObj));

                System.out.println("\nJSON:");
                JsonArray array = JsonParser.parseString(json).getAsJsonArray();
                System.out.println(gson.toJson(array));

                System.out.println("\nJSON Elements Iterate:");
                for (JsonElement elm : array) {
                    System.out.println(elm.getAsJsonObject().get("predictedClass").getAsString());
                }
                for (int i=0; i < array.size(); i++) {
                    System.out.println(array.get(i).getAsJsonObject().get("confidence").getAsDouble());
                }
            }
            else {
                System.out.println(">>> Request Failed!");
                System.out.println("Reason: " + response.getStatusLine());
            }
        }
        catch (Exception e) {
            System.out.println("Response Error:");
            e.printStackTrace();
        }
    }

    private static void simplePostMultipart() {
        try {
            URL url = new URL("url");

            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpPost httpPost = new HttpPost(url.toURI());

            StringBody name = new StringBody("Jon Doe", ContentType.MULTIPART_FORM_DATA);
            StringBody age = new StringBody("27", ContentType.MULTIPART_FORM_DATA);

            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("name", name)
                    .addPart("age", age)
                    .build();

            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(">>> Request successful!");

                System.out.println("\n>>> Response String:");
                String json = EntityUtils.toString(response.getEntity());
                System.out.println(json);

                System.out.println("\n>>> Response JSON:");
                JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(jsonObj));
            }
            else {
                System.out.println(">>> Request Failed!");
                System.out.println("Reason: " + response.getStatusLine());
            }
        }
        catch (Exception e) {
            System.out.println("Response Error:");
            e.printStackTrace();
        }
    }

    private static void simplePostMultipartImage() {
        try {
            URL url = new URL("url");

            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpPost httpPost = new HttpPost(url.toURI());

            File file = new File(ServiceConsumerTest.class.getResource("test.jpg").getFile());
            FileBody image = new FileBody(file);

            StringBody threshold = new StringBody("0.75", ContentType.MULTIPART_FORM_DATA);

            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("image", image)
                    .addPart("threshold", threshold)
                    .build();

            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(">>> Request successful!");

                System.out.println("\n>>> Response String:");
                String jsonString = EntityUtils.toString(response.getEntity());
                System.out.println(jsonString);

                System.out.println("\n>>> Response JSON:");
                JsonObject jsonObj = JsonParser.parseString(jsonString).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(jsonObj));
            }
            else {
                System.out.println(">>> Request Failed!");
                System.out.println("Reason: " + response.getStatusLine());
            }
        }
        catch (Exception e) {
            System.out.println("Response Error:");
            e.printStackTrace();
        }
    }
}