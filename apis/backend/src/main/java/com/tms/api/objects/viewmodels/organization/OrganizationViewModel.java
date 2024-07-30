package com.tms.api.objects.viewmodels.organization;

import com.tms.api.objects.models.organization.OrganizationDetailModel;
import com.tms.api.objects.models.organization.OrganizationModel;

public record OrganizationViewModel(
    OrganizationModel organization,
    OrganizationDetailModel organizationDetail
) {}