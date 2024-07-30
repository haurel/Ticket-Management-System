import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "@environments/environment";
import {
    JwtUserModel,
    LoginUserRequestModel,
    RegisterUserRequestModel,
    ResponseModel
} from "../../models";

@Injectable({ providedIn: 'root' })
export class AuthService {
    
    constructor(private router: Router,
        private http: HttpClient){}

    public Login(request: LoginUserRequestModel) {
        return this.http.post<ResponseModel<JwtUserModel>>(`${environment.apiUrl}auth/login`, request);
    }

    public Register(request: RegisterUserRequestModel) {
        return this.http.post<ResponseModel<boolean>>(`${environment.apiUrl}auth/register`, request);
    }
}