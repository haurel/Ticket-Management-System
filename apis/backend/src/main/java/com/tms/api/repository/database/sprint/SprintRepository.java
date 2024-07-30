package com.tms.api.repository.database.sprint;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tms.api.objects.models.sprint.document.SprintDocument;
import com.tms.api.objects.types.task.SprintType;

@Repository
public interface SprintRepository extends MongoRepository<SprintDocument, String> {
    List<SprintDocument> findAllByProjectId(String projectId);
    SprintDocument findByProjectIdAndSprintType(String projectId, SprintType sprintType);
}
