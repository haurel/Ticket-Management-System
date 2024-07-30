package com.logging.repository.database.logging;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.logging.objects.logging.LoggingDocument;

@Repository
public interface LoggingRepository extends MongoRepository<LoggingDocument, String> {
    
}
