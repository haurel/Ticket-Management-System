package com.common.repository.logging;

import java.net.http.HttpClient;

import org.springframework.stereotype.Repository;

import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.logging.LoggingRequestModel;
import com.common.objects.models.request.RequestModel;
import com.common.repository.BaseApiRepository;
import com.common.types.api.ResponseStatusType;
import com.configuration.CommonProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LoggingApiRepository extends BaseApiRepository {
    private String _loggingApiUrl;

    public LoggingApiRepository(HttpClient httpClient,
        ObjectMapper objectMapper,
        CommonProperties commonProperties) {
        super(httpClient, objectMapper, commonProperties);
        _loggingApiUrl = commonProperties.getLoggingApiUrl();
    }

    public ResponseModel<Boolean> LogException(LoggingRequestModel loggingRequest) {
        var objectMapper = new ObjectMapper();
        ResponseModel<Boolean> response = new ResponseModel<Boolean>(false, ResponseStatusType.Error.toString(), ResponseStatusType.Error);
        try {
            var jsonRequest = objectMapper.writeValueAsString(loggingRequest);

            var request = new RequestModel(_loggingApiUrl, "logging", "excepetion", jsonRequest, null);
            response = Post(request, Boolean.class);
            return response;

        } catch (Exception exception) {
            exception.printStackTrace();
            return response;
        }
    }
}
