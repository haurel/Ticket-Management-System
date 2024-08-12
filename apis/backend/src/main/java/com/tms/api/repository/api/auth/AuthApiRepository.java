package com.tms.api.repository.api.auth;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.common.helpers.apis.AuthApi;
import com.common.objects.models.request.RequestModel;
import com.common.objects.models.user.UserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;
import com.common.repository.BaseApiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AuthApiRepository extends BaseApiRepository {
    @Value("${authapi.url}")
    private String _authApiUrl;

    public AuthApiRepository(HttpClient httpClient,
        ObjectMapper objectMapper) {
        super(httpClient, objectMapper);
    }

    public UserModel Login(LoginUserRequestModel loginUserRequest) {
        var objectMapper = new ObjectMapper();
        UserModel userResponse = null;
        try {
            var jsonRequest = objectMapper.writeValueAsString(loginUserRequest);

            var request = new RequestModel(_authApiUrl, AuthApi.Controller.Auth, AuthApi.Action.Login, jsonRequest, null);
            userResponse = Post(request, UserModel.class);
            return userResponse;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return userResponse;
        }
    }

    public Boolean Register(RegisterUserRequestModel registerUserRequest) {
        var objectMapper = new ObjectMapper();
        try {
            var jsonRequest = objectMapper.writeValueAsString(registerUserRequest);
            var request = new RequestModel(_authApiUrl, AuthApi.Controller.Auth, AuthApi.Action.Login, jsonRequest, null);
            Post(request, Boolean.class);
            //TODO handle response from AuthAPI
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
