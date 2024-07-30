package com.tms.api.service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Service;

import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.logging.LoggingRequestModel;
import com.common.repository.logging.LoggingApiRepository;
import com.common.types.api.ResponseStatusType;
import com.common.types.logging.LoggingSourceType;

@Service
public class BaseService {

    private final LoggingApiRepository _loggingApiRepository;
    protected final ModelMapper _modelMapper;

    public BaseService(LoggingApiRepository loggingApiRepository,
        ModelMapper modelMapper
    ) {
        _loggingApiRepository = loggingApiRepository;
        _modelMapper = modelMapper;
        _modelMapper.registerModule(new RecordModule());
    }

    public <T> ResponseModel<T> GetResponse(T object, String responseMessage) {
        var responseStatus = object == null ? ResponseStatusType.Error : ResponseStatusType.Success;

        return new ResponseModel<T>(object, responseMessage, responseStatus);
    }
    
    public void LogException(Exception exception, Map<String, Object> map) {
        var loggingRequest = GetLoggingRequest(exception.getMessage(), map);
        _loggingApiRepository.LogException(loggingRequest);
    }

    public Date GetDateNow(){
        return Date.from(Instant.now());
    }

    private LoggingRequestModel GetLoggingRequest(String message, Object obj) {
        return new LoggingRequestModel(LoggingSourceType.BackendApi, message, obj, Date.from(Instant.now()));
    }
}
