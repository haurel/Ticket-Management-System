package com.auth.authapi.objects.models.User;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record UserDocument(
    @MongoId(FieldType.OBJECT_ID)
    String id,
    String userName,
    String emailAddress,
    String password,
    List<String> organizationIds
) {}
