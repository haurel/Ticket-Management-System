package com.tms.api.objects.models.user;

import com.tms.api.objects.types.user.UserRoleType;

public record UserPermissionModel(
    String userId,
    UserRoleType userRoleType
) {}
