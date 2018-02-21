package ru.task1.cvfinder.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public class HttpMethodUtils {
    private static String baseUrl;
    private static CloseableHttpClient httpClient;
    private static String userAgent;

    static {
        baseUrl = "https://api.zp.ru/v1/";
        httpClient = HttpClients.createDefault();
        userAgent = "FindResume/1.0";
    }
    public static String getMethod(String urlPath) throws IOException {
        if (urlPath == null) return null;
        HttpGet httpGet = new HttpGet(baseUrl + urlPath);
        httpGet.addHeader("User-Agent", userAgent);
        httpGet.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        return getResponse(httpGet);
    }

    public static String postMethod(String urlPath, List<NameValuePair> params) throws IOException {
        if (urlPath == null || params == null) return null;
        HttpPost httpPost = new HttpPost(baseUrl + urlPath);
        httpPost.addHeader("User-Agent", userAgent);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        return getResponse(httpPost);

    }

    private static String getResponse(HttpUriRequest httpRequest) throws IOException {
        CloseableHttpResponse response = httpClient.execute(httpRequest);
        StringBuilder serverAnswer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        while (reader.ready()) {
            serverAnswer.append(reader.readLine());
           // System.out.println(reader.readLine());
        }
        reader.close();
        response.close();
        return serverAnswer.toString();
    }


}
