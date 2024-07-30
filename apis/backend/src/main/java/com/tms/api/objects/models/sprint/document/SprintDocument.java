package com.tms.api.objects.models.sprint.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.tms.api.objects.models.sprint.SprintTaskModel;
import com.tms.api.objects.types.task.SprintType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "sprint")
public class SprintDocument {
    @MongoId(FieldType.OBJECT_ID)
    @Field(name="id")
    private String sprintId;
    private String sprintName;
    private String projectId;
    private List<SprintTaskModel> tasks;
    private SprintType sprintType;
    private Date startDate;
    private Date endDate;
}

