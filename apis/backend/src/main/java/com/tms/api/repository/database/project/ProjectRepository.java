package com.tms.api.repository.database.project;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tms.api.objects.models.project.document.ProjectDocument;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectDocument, String> {
    public List<ProjectDocument> findByOrganizationId(String organizationId);
}
