package com.common.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Repository;

import com.common.helpers.GlobalProperties;
import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.request.RequestModel;
import com.configuration.CommonProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Repository
public class BaseApiRepository {
    
    private HttpClient _httpClient;
    private ObjectMapper _objectMapper;
    private CommonProperties _commonProperties;

    public BaseApiRepository(HttpClient httpClient,
        ObjectMapper objectMapper,
        CommonProperties commonProperties) {
        super();
        _httpClient = httpClient;
        _objectMapper = objectMapper;
        _commonProperties = commonProperties;
    }

    public <T> ResponseModel<T> Post(RequestModel request, Class<T> classType) throws Exception {
        var uri = UrlBuilder(request.baseUrl(), request.controller(), request.action());
        var httpRequest = HttpRequest
            .newBuilder()
            .uri(uri)
            .header("Content-Type", request.contentType())
            .header(GlobalProperties.API_KEY_HEADER, _commonProperties.getApiToken())
            .POST(HttpRequest.BodyPublishers.ofString(request.requestBody()))
            .build();

        HttpResponse<String> httpResponse;
        ResponseModel<T> response = null;
        try {
            httpResponse = _httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            var body = httpResponse.body();
            response = DeserializeResponse(body, classType);
        } catch (Exception exception) {
            throw exception;
        }

        return response;
    }

    private <T> ResponseModel<T> DeserializeResponse(String body, Class<T> classType) throws Exception {
        //TypeReference<T> typeReference = new TypeReference<T>() {};
        ResponseModel<T> response = null;

        if (body == null || body.isEmpty()) {
            return response;
        }

        try {
            //response = _objectMapper.readValue(body, typeReference);
            var type = TypeFactory.defaultInstance().constructParametricType(ResponseModel.class, classType);
            response = _objectMapper.readValue(body, type) ;
        } catch (Exception exception) {
            throw exception;
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
