package com.tms.api.objects.models.sprint;

import com.tms.api.objects.models.user.sprint.TaskUserDetailModel;
import com.tms.api.objects.types.task.TaskType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SprintTaskModel {
    private String taskId;
    private String description;
    private TaskType taskType;
    private TaskUserDetailModel assignee;
    private TaskUserDetailModel owner;

    public SprintTaskModel(String taskId, String description, TaskType taskType, TaskUserDetailModel assignee, TaskUserDetailModel owner) {
        this.taskId = taskId;
        this.description = description;
        this.taskType = taskType;
        this.assignee = assignee;
        this.owner = owner;
    }
}
