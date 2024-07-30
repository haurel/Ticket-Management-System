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

        this.authService.Login(this.LoginRequest)
            .subscribe({
                next: (response) => {
                    if (!response.Response
                        || response.ResponseStatus == ResponseStatusType.Error
                    ) {
                        this.DisplayMessage(response.Message, NotificationType.Error);
                    } else {
                        localStorage.setItem('user', JSON.stringify(response.Response));

                        const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
                        this.router.navigateByUrl(returnUrl);
                    }
                },
                error: error => {
                    this.DisplayMessage(error, NotificationType.Error);
                    // this.loading = false;
                }
            });
    }

    public Register() {
        this.router.navigateByUrl('/p/register');
    }
}