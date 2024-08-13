package com.tms.api.objects.models.sprint;

import java.util.Date;
import java.util.List;

import com.tms.api.objects.types.task.SprintType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SprintModel {
    private String sprintId;
    private String sprintName;
    private String projectId;
    private List<SprintTaskModel> tasks;
    private SprintType sprintType;
    private Date startDate;
    private Date endDate;
}
