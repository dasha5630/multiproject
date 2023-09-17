package com.tdv.tech.multiproject.service;

import org.json.JSONObject;
import org.jvnet.hk2.annotations.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CurrencyService {

    public static String getCurrencyRate(String message) throws Exception {
        String response = apiRequest();
        JSONObject object = new JSONObject(response);
        JSONObject res = object.getJSONObject("rates");
        return res.get(message).toString();
    }

    public static String apiRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.cbr-xml-daily.ru/latest.js"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
