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
    private String SprintId;
    private String SprintName;
    private String ProjectId;
    private List<SprintTaskModel> Tasks;
    private SprintType SprintType;
    private Date StartDate;
    private Date EndDate;
}
