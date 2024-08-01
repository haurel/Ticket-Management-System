package com.auth.authapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authapi.service.auth.AuthService;
import com.common.helpers.apis.AuthApi;
import com.common.objects.models.user.UserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;


@RestController
@RequestMapping(AuthApi.Controller.Auth)
public class AuthController {
    
    private final AuthService _authService;

    public AuthController(AuthService authService) {
        _authService = authService;
    }

    @PostMapping(AuthApi.Action.Login)
    public UserModel Login(@RequestBody LoginUserRequestModel request) {
        return _authService.Login(request);
    }
    
    @PostMapping(AuthApi.Action.Register)
    public Boolean Register(@RequestBody RegisterUserRequestModel request) {
        _authService.Register(request);
        return true;
    }
}
