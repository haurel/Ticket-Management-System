import { Component, Injector, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { NotificationType, ResponseStatusType } from "../../../core/enums";
import { LoginUserRequestModel } from "../../../core/models";
import { AuthService } from "../../../core/services";
import { BasePage } from "../../../shared/pages";


@Component({
    templateUrl: './login.page.component.html',
    styleUrls: ['./login.page.component.scss'],
})
export class LoginPageComponent extends BasePage implements OnInit {
    public LoginForm!: FormGroup;
    public LoginRequest: LoginUserRequestModel = new LoginUserRequestModel();

    constructor(injector: Injector,
        private route: ActivatedRoute,
        private router: Router,
        private formBuilder: FormBuilder,
        private authService: AuthService,
    ) {
        super(injector);
    }

    public ngOnInit(): void {
        this.LoginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }
    
    public Login() {
        this.LoginRequest.Username = this.LoginForm.get('username')?.value;
        this.LoginRequest.Password = this.LoginForm.get('password')?.value;

        this.CallApi(
            this.authService.Login(this.LoginRequest),
            (response) => {
                if (!response.response
                    || response.responseStatus == ResponseStatusType.Error
                ) {
                    this.DisplayMessage(response.message, NotificationType.Error);
                } else {
                    localStorage.setItem('user', JSON.stringify(response.response));

                    const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
                    this.router.navigateByUrl(returnUrl);
                }
            }
        )
    }

    public Register() {
        this.router.navigateByUrl('/p/register');
    }
}