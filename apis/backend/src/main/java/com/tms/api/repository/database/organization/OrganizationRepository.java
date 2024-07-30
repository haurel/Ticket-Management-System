package com.tms.api.repository.database.organization;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tms.api.objects.models.organization.document.OrganizationDocument;

@Repository
public interface OrganizationRepository extends MongoRepository<OrganizationDocument, String> { //, CustomOrganizationRepository {
}