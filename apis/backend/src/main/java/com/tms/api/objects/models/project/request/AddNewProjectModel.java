package com.tms.api.objects.models.project.request;

import java.util.List;

import com.tms.api.objects.models.user.UserPermissionModel;

public record AddNewProjectModel(
    String organizationId,
    String projectName,
    List<UserPermissionModel> users
) {}
