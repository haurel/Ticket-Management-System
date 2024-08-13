package com.auth.authapi.service.auth;

import java.text.MessageFormat;
import java.util.HashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authapi.objects.models.User.UserDocument;
import com.auth.authapi.repository.auth.AuthRepository;
import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.user.UserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;
import com.common.repository.logging.LoggingApiRepository;
import com.common.service.BaseService;
import com.common.types.api.ResponseStatusType;

@Service
public class AuthService extends BaseService {
    
	private BCryptPasswordEncoder _bCryptPasswordEncoder;
    private AuthRepository _authRepository;

    public AuthService(AuthRepository authRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        LoggingApiRepository loggingApiRepository,
        ModelMapper modelMapper
    ) {
        super(loggingApiRepository, modelMapper);
        _authRepository = authRepository;
        _bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseModel<UserModel> Login(LoginUserRequestModel request) {
        try {
            var userDocument = _authRepository.findByUserName(request.Username());
            if (userDocument == null) {
                return GetResponse(null, "You have entered an invalid username or password!");
            }

            var passwordMatched = _bCryptPasswordEncoder.matches(request.Password(), userDocument.password());
            if (passwordMatched) {
                var response = new UserModel(userDocument.id(), userDocument.userName(), userDocument.emailAddress());
                return GetResponse(response, ResponseStatusType.Success.toString());
            }

            return GetResponse(null, "You have entered an invalid username or password!");
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "Login");
            map.put("Request", MessageFormat.format("Login failed for Username: {0}", request.Username()));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);
            
            return GetResponse(null, ResponseStatusType.Error.toString());
        }
    }

    public ResponseModel<Boolean> Register(RegisterUserRequestModel request) {
        var userAlreadyExists = CheckIfUserExists(request);
        if (userAlreadyExists) {
            return GetResponse(false, "User already exists!");
        }

        try {
            var password = _bCryptPasswordEncoder.encode(request.Password());
            var userDocument = new UserDocument(null, request.Username(), request.EmailAddress(), password, null);
            _authRepository.save(userDocument);
            return GetResponse(true, "Account created succesfully");
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "Register");
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(false, ResponseStatusType.Error.toString());
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
