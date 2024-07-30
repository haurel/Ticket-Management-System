package com.tms.api.objects.models.project;

import java.util.Date;
import java.util.List;

import com.tms.api.objects.models.user.UserPermissionModel;
import com.tms.api.objects.types.StatusType;

public record ProjectModel(
    String projectId,
    String organizationId,
    String projectGuid,
    String projectName,
    List<UserPermissionModel> Users,
    StatusType statusId,
    Date createdDate,
    Date modifiedDate
) {}