package com.tms.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.objects.models.api.ResponseModel;
import com.common.objects.models.jwt.JwtUserModel;
import com.common.objects.models.user.request.LoginUserRequestModel;
import com.common.objects.models.user.request.RegisterUserRequestModel;
import com.tms.api.service.auth.AuthService;


@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {
    
    private final AuthService _authService;
    
    public AuthController(AuthService authService) {
         _authService = authService;
    }

    @PostMapping("register")
    public ResponseModel<Boolean> Register(@RequestBody RegisterUserRequestModel request) {
        var response = _authService.Register(request);
        return response;
    }
    
    @PostMapping("login")
    public ResponseModel<JwtUserModel> Login(@RequestBody LoginUserRequestModel request) {
        var response = _authService.Login(request);
        return response;
    }
    
}