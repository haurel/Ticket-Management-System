package com.tms.api.service.auth;

import java.text.MessageFormat;
import java.util.HashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.jwt.JwtUserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;
import com.common.repository.logging.LoggingApiRepository;
import com.common.service.BaseService;
import com.tms.api.configuration.JwtUtil;
import com.tms.api.repository.api.auth.AuthApiRepository;

@Service
public class AuthService extends BaseService {

    private final AuthApiRepository _authApiRepository;
    private final JwtUtil _jwtUtil;

    public AuthService(AuthApiRepository authApiRepository,
        LoggingApiRepository loggingApiRepository,
        JwtUtil jwtUtil,
        ModelMapper modelMapper
    ) {
        super(loggingApiRepository, modelMapper);
        _authApiRepository = authApiRepository;
        _jwtUtil = jwtUtil;
    }
    
    public ResponseModel<JwtUserModel> Login(LoginUserRequestModel request) {
        try {
            var loginUserReponse = _authApiRepository.Login(request);
            if (loginUserReponse == null
                || loginUserReponse.getResponse() == null) {
                return GetResponse(null, "Username or password is incorrect!");
            }

            var user = loginUserReponse.getResponse();
            var token = _jwtUtil.createToken(user);
            var jwtUser = new JwtUserModel(user.getUserId(), user.getUsername(), token);
            return GetResponse(jwtUser, null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "Login");
            map.put("Request", MessageFormat.format("Login failed for Username: {0}", request.Username()));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<Boolean> Register(RegisterUserRequestModel request) {
        try {
            var response = _authApiRepository.Register(request);
            return response;
        } catch (Exception exception) {
            var logRequest = new RegisterUserRequestModel(request.Username(), null, request.EmailAddress());
            var map = new HashMap<String, Object>();
            map.put("Method", "Register");
            map.put("Request", logRequest);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }
}
