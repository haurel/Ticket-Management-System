package com.tms.api.objects.models.organization.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.tms.api.objects.types.user.UserRoleType;

public record OrganizationDetailDocument(
    @MongoId(FieldType.OBJECT_ID)
    String id,
    String organizationId,
    UserRoleType OrganizationUserRoleType,
    Date createdDate
) {}
