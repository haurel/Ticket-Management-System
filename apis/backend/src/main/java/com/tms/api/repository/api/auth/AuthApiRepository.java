package com.tms.api.repository.api.auth;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.common.helpers.apis.AuthApi;
import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.request.RequestModel;
import com.common.objects.models.user.UserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;
import com.common.repository.BaseApiRepository;
import com.configuration.CommonProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AuthApiRepository extends BaseApiRepository {
    @Value("${authapi.url}")
    private String _authApiUrl;

    public AuthApiRepository(HttpClient httpClient,
        ObjectMapper objectMapper,
        CommonProperties commonProperties) {
        super(httpClient, objectMapper, commonProperties);
    }

    public ResponseModel<UserModel> Login(LoginUserRequestModel loginUserRequest) throws Exception {
        var objectMapper = new ObjectMapper();
        ResponseModel<UserModel> userResponse = null;
        try {
            var jsonRequest = objectMapper.writeValueAsString(loginUserRequest);

            var request = new RequestModel(_authApiUrl, AuthApi.Controller.Auth, AuthApi.Action.Login, jsonRequest, null);
            userResponse = Post(request, UserModel.class);
            return userResponse;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public ResponseModel<Boolean> Register(RegisterUserRequestModel registerUserRequest) throws Exception {
        var objectMapper = new ObjectMapper();
        ResponseModel<Boolean> response = null;
        try {
            var jsonRequest = objectMapper.writeValueAsString(registerUserRequest);
            var request = new RequestModel(_authApiUrl, AuthApi.Controller.Auth, AuthApi.Action.Login, jsonRequest, null);
            response = Post(request, Boolean.class);
            return response;
        } catch (Exception exception) {
            throw exception;
        }
    }
}
