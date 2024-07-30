package com.auth.authapi.service.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authapi.objects.models.User.UserDocument;
import com.auth.authapi.repository.auth.AuthRepository;
import com.common.objects.models.user.UserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;

@Service
public class AuthService {
    
	private BCryptPasswordEncoder _bCryptPasswordEncoder;
    private AuthRepository _authRepository;

    public AuthService(AuthRepository authRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        _authRepository = authRepository;
        _bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserModel Login(LoginUserRequestModel request) {
        var userDocument = _authRepository.findByUserName(request.Username());
        if (userDocument == null) {
            return null;
        }

        var passwordMatched = _bCryptPasswordEncoder.matches(request.Password(), userDocument.password());
        if (passwordMatched) {
            return new UserModel(userDocument.id(), userDocument.userName(), userDocument.emailAddress());
        }

        return null;
    }

    public Boolean Register(RegisterUserRequestModel request) {
        var userAlreadyExists = CheckIfUserExists(request);
        if (userAlreadyExists) {
            return false;
        }

        try {
            var password = _bCryptPasswordEncoder.encode(request.Password());
            var userDocument = new UserDocument(null, request.Username(), request.EmailAddress(), password, null);
            _authRepository.save(userDocument);
            return true;
        } catch (Exception e) {
            //TODO log error in LoggingApi
            return false;
        }
    }

    private Boolean CheckIfUserExists(RegisterUserRequestModel request) {
        try {
            var user = _authRepository.findByUserNameAndEmailAddress(request.Username(), request.EmailAddress());
            if (user == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            //TODO log error
            return false;
        }
    }
}
