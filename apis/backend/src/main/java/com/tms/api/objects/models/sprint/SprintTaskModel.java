package com.tms.api.objects.models.sprint;

import com.tms.api.objects.models.user.sprint.TaskUserDetailModel;
import com.tms.api.objects.types.task.TaskType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SprintTaskModel {
    private String taskId;
    private String description;
    private TaskType taskType;
    private TaskUserDetailModel assignee;
    private TaskUserDetailModel owner;
}
