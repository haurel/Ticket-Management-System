package com.common.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Repository;

import com.common.helpers.GlobalProperties;
import com.common.objects.models.request.RequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BaseApiRepository {
    
    private HttpClient _httpClient;
    private ObjectMapper _objectMapper;

    public BaseApiRepository(HttpClient httpClient,
        ObjectMapper objectMapper) {
        super();
        _httpClient = httpClient;
        _objectMapper = objectMapper;
    }

    public <T> T Post(RequestModel request, Class<T> classType) {
        var uri = UrlBuilder(request.baseUrl(), request.controller(), request.action());

        var httpRequest = HttpRequest
            .newBuilder()
            .uri(uri)
            .header("Content-Type", request.contentType())
            .header(GlobalProperties.API_KEY_HEADER, GlobalProperties.API_TOKEN)
            .POST(HttpRequest.BodyPublishers.ofString(request.requestBody()))
            .build();

        HttpResponse<String> httpResponse;
        T response = null;
        try {
            httpResponse = _httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            var body = httpResponse.body();
            response = DeserializeResponse(body, classType);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    private <T> T DeserializeResponse(String body, Class<T> classType) {
        //TypeReference<T> typeReference = new TypeReference<T>() {};
        T response = null;

        if (body == null || body.isEmpty()) {
            return response;
        }

        try {
            //response = _objectMapper.readValue(body, typeReference);
            response = _objectMapper.readValue(body, classType);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    private URI UrlBuilder(String baseUrl, String controller, String action) {
        String url = baseUrl + "/" + controller + "/" + action;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return uri;
    }
}
