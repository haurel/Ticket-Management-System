package com.tms.api.objects.models.sprint.request;

import java.sql.Date;

import com.tms.api.objects.types.task.SprintType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateSprintRequestModel {
    private String SprintId;
    private SprintType SprintType;
    private Date EndDate;
}
