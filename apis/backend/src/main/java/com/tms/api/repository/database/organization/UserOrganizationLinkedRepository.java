package com.tms.api.repository.database.organization;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tms.api.objects.models.organization.document.UserOrganizationLinkedDocument;

@Repository
public interface UserOrganizationLinkedRepository extends MongoRepository<UserOrganizationLinkedDocument, String> {
    public UserOrganizationLinkedDocument findByUserId(String userId);
}
