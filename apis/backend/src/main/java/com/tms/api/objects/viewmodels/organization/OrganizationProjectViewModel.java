package com.tms.api.objects.viewmodels.organization;

import java.util.List;

import com.tms.api.objects.models.organization.OrganizationModel;
import com.tms.api.objects.models.project.ProjectModel;

public record OrganizationProjectViewModel(
    OrganizationModel organization,
    List<ProjectModel> projects
) {}
