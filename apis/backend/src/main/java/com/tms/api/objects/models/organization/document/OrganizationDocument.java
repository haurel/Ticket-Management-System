package com.tms.api.objects.models.organization.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection="organization")
public record OrganizationDocument(
    @MongoId(FieldType.OBJECT_ID)
    String id,
    String name,
    Date createdDate,
    Date modifiedDate
){
}
