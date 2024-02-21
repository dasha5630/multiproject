package com.tdv.tech.multiproject.service;

import org.json.JSONObject;
import org.jvnet.hk2.annotations.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * CurrencyService requests currency rate from <a href="https://www.cbr-xml-daily.ru">...</a>
 *
 * @author Tikhanova Daria
 * @since 21.02.2024
 */
@Service
public class CurrencyService {

    private static final String URL = "https://www.cbr-xml-daily.ru/latest.js";
    private CurrencyService(){}

    public static String getCurrencyRate(String message) throws java.io.IOException, java.lang.InterruptedException {
        String response = apiRequest();
        JSONObject object = new JSONObject(response);
        JSONObject res = object.getJSONObject("rates");
        return res.get(message).toString();
    }

    public static String apiRequest() throws java.io.IOException, java.lang.InterruptedException {
        try(HttpClient client = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }

}
