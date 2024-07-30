package com.logging.objects.logging;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.common.types.logging.LoggingSourceType;
import com.common.types.logging.LoggingType;

@Document(collection = "logging")
public record LoggingDocument(
    @MongoId(FieldType.OBJECT_ID)
    String id,
    LoggingType loggingType,
    LoggingSourceType loggingSourceType,
    String message,
    Object context,
    Date createdDate
) {}
