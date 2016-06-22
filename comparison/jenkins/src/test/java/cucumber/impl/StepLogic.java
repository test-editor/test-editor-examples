package cucumber.impl;

import java.net.HttpURLConnection;
import java.net.URL;

public class StepLogic {

    public boolean checkUrlAvailibility(String arg1) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(arg1).openConnection();
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
