package com.logging.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.objects.models.logging.LoggingRequestModel;
import com.logging.services.logging.LoggingService;


@RestController
@RequestMapping("logging")
public class LoggingController {
    
    private final LoggingService _loggingService;

    public LoggingController(LoggingService loggingService) {
        _loggingService = loggingService;
    }

    @PostMapping("information")
    public Boolean LogInformation(@RequestBody LoggingRequestModel request) {
        _loggingService.LogInformation(request);
        return true;
    }

    @PostMapping("warning")
    public Boolean LogWarning(@RequestBody LoggingRequestModel request) {
        _loggingService.LogWarning(request);
        return true;
    }

    @PostMapping("excepetion")
    public Boolean LogException(@RequestBody LoggingRequestModel request) {
        _loggingService.LogException(request);
        return true;
    }
    
}
