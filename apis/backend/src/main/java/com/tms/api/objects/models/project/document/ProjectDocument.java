package com.tms.api.objects.models.project.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.tms.api.objects.models.user.UserPermissionModel;
import com.tms.api.objects.types.StatusType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "project")
public class ProjectDocument {
    @MongoId(FieldType.OBJECT_ID)
    @Field("id")
    private String projectId;
    private String organizationId;
    private String projectGuid;
    private String projectName;
    private List<UserPermissionModel> Users;
    private StatusType statusId;
    private Date createdDate;
    private Date modifiedDate;
}
