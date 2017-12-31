package maho;

import maho.jaxrs.RestClient;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HelloService {
    RestClient restClient = new RestClient();
    URI uri = URI.create("http://localhost:8081/rest/api/hello");

    public String hello() {
        WebTarget target = restClient.getTarget(uri);
        return target.request(MediaType.TEXT_PLAIN)
                     .get(String.class);
    }

    public String hej() {
        try {

            URL url = new URL("http://localhost:8081/rest/hello");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                                                   + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return "nej";

    }


}
