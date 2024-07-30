package com.tms.api.objects.models.organization.api;

public record AddNewOrganizationRequestModel(
    String userId,
    String organizationName
) {}
