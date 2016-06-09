package de.signaliduna.showcase.testpyramide.jenkinsClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class JenkinsClient {

    private static final String REQUEST_PARAM_NAME = "name=";
    private static final String CREATE_OPERATION = "/createItem";
    private static final String DELETE_OPERATION = "/doDelete";
    private static final String BASE_URL = "http://jenkinsUserSsKey@localhost:8080";

    public String createJob(String name, File jobConfigFile) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URL + CREATE_OPERATION + "?" + REQUEST_PARAM_NAME + name);

        Response post = webTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(jobConfigFile));
        return post.getStatus() + "";
    }

    public String deleteJob(String name) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URL + "/job/" + name + DELETE_OPERATION);

        Response post = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.text(""));
        return post.getStatus() + "";
    }

    public boolean checkIfJobExists(String name) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(BASE_URL + "/job/" + name).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
