package test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import java.io.File;

public class FileClient {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();

        Request req = httpClient.newRequest("http://127.0.0.1:8080/fileList").method(HttpMethod.POST);
        req.header(HttpHeader.CONTENT_TYPE, "application/json");
        req.content(new StringContentProvider(getFileList(), "utf-8"));

        ContentResponse response = req.send();
        System.out.println(response.getContentAsString());

        httpClient.stop();
    }

    private static String getFileList() {
        File dir = new File("./INPUT");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Folder", dir.getName());

        JsonArray jsonArray = new JsonArray();

        for(File file : dir.listFiles()) {
            jsonArray.add(file.getName());
        }
        jsonObject.add("FILES", jsonArray);


        return jsonObject.toString();
    }


}
