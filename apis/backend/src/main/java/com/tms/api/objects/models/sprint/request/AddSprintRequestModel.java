package com.tms.api.objects.models.sprint.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddSprintRequestModel {
    private String ProjectId;
    private String SprintName;
}
