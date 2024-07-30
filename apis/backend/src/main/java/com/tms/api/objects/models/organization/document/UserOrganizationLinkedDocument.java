package com.tms.api.objects.models.organization.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.tms.api.objects.models.organization.OrganizationDetailModel;

@Document(collection="userOrganizationLinkedDocument")
public record UserOrganizationLinkedDocument(
    @MongoId(FieldType.OBJECT_ID)
    String id,
    List<OrganizationDetailModel> organizationDetails,
    String userId,
    Date createdDate
){}
