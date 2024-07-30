package com.logging.services.logging;

import org.springframework.stereotype.Service;

import com.common.objects.models.logging.LoggingRequestModel;
import com.common.types.logging.LoggingType;
import com.logging.objects.logging.LoggingDocument;
import com.logging.repository.database.logging.LoggingRepository;

@Service
public class LoggingService {
    
    private final LoggingRepository _loggingRepository;

    public LoggingService(LoggingRepository loggingRepository) {
        _loggingRepository = loggingRepository;
    }

    public Boolean LogInformation(LoggingRequestModel request) {
        _loggingRepository.save(ConvertToLoggingDocument(request, LoggingType.Information));
        return true;
    }

    public Boolean LogWarning(LoggingRequestModel request) {
        _loggingRepository.save(ConvertToLoggingDocument(request, LoggingType.Warning));
        return true;
    }

    public Boolean LogException(LoggingRequestModel request) {
        _loggingRepository.save(ConvertToLoggingDocument(request, LoggingType.Error));
        return true;
    }

    private LoggingDocument ConvertToLoggingDocument(LoggingRequestModel request, LoggingType loggingType) {
        return new LoggingDocument(null, loggingType, request.loggingSourceType(), request.message(), request.context(), request.createdDate());
    }
}
