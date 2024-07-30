package com.tms.api.objects.models.organization;

import java.util.Date;

import com.tms.api.objects.types.user.UserRoleType;

public record OrganizationDetailModel(
    String organizationId,
    UserRoleType organizationUserRoleType,
    Date createdDate,
    String createdUserId,
    Date modifiedDate,
    String modifiedUserId
) {}
