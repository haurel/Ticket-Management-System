package com.auth.authapi.repository.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.auth.authapi.objects.models.User.UserDocument;

@Repository
public interface AuthRepository extends MongoRepository<UserDocument, String> {
    UserDocument findByUserNameAndEmailAddress(String userName, String emailAddress);
    UserDocument findByUserName(String userName);
}
