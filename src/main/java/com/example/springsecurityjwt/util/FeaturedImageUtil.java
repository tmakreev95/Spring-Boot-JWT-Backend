package com.example.springsecurityjwt.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class FeaturedImageUtil {
    private static final String BASE_UPLOAD_FOLDER = "http://localhost:8012/storage/post-featured-images/";
    private static final String STORAGE_HANDLER_URl = "http://localhost:8012/storage/storage-handler.php";
    private static final String SERVER_AUTHENTICATION = "webadmin:123456";

    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public Map<String, String> persistImage(String fileContents, String imageName) {
        Map<String, String> configuration = new HashMap();

        try {
            String auth = SERVER_AUTHENTICATION;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);

            String newFileName = System.currentTimeMillis() + imageName;

            URL url = new URL(STORAGE_HANDLER_URl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authHeaderValue);

            Map<String, String> requestParameters = new HashMap<>();
            requestParameters.put("imageName", newFileName);
            requestParameters.put("fileContent", fileContents);

            configuration.put("filePath", BASE_UPLOAD_FOLDER + newFileName);
            configuration.put("featuredImageName", newFileName);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(getParamsString(requestParameters));
            out.flush();
            out.close();

            int code = connection.getResponseCode();

            return configuration;
        }
        catch(Exception e) {

        }

        return configuration;
    }
}
