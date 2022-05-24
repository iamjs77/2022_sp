package card.validator.manager;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class ManagerClient {

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();

        Request req = httpClient.newRequest("http://127.0.0.1:8081/REPORT/MANAGERID/20220518").method(HttpMethod.GET);

        ContentResponse response = req.send();
        System.out.println(response.getContentAsString());

        httpClient.stop();
    }
}
