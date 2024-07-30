package com.common.objects.models.logging;

import java.util.Date;

import com.common.types.logging.LoggingSourceType;

public record LoggingRequestModel(
    LoggingSourceType loggingSourceType,
    String message,
    Object context,
    Date createdDate
) {}
