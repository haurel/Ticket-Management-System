package com.tms.api.objects.models.organization;

import java.util.Date;

public record OrganizationModel(
    String id,
    String name,
    Date createdDate,
    Date modifiedDate
) {
}
