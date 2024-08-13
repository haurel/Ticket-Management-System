import { Component, Injector, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { NotificationType, ResponseStatusType } from "../../../core/enums";
import { RegisterUserRequestModel } from "../../../core/models";
import { AuthService } from "../../../core/services";
import { BasePage } from "../../../shared/pages";

@Component({
    templateUrl: './register.page.component.html',
    styleUrls: ['register.page.component.scss'],
})
export class RegisterPageComponent extends BasePage implements OnInit {
    public RegisterForm!: FormGroup;
    public RegisterRequest: RegisterUserRequestModel = new RegisterUserRequestModel();

    constructor(injector: Injector,
        private router: Router,
        private formBuilder: FormBuilder,
        private authService: AuthService,
    ) {
        super(injector);
    }

    public ngOnInit(): void {
        this.RegisterForm = this.formBuilder.group({
            username: ['', [Validators.required]],
            password: ['', [Validators.required]],
            emailAddress: ['', [Validators.email, Validators.required]],
        });
    }
    
    public Register() {
        this.RegisterRequest.Username = this.RegisterForm.get('username')?.value;
        this.RegisterRequest.EmailAddress = this.RegisterForm.get('emailAddress')?.value;
        this.RegisterRequest.Password = this.RegisterForm.get('password')?.value;

        this.authService.Register(this.RegisterRequest)
            .subscribe({
                next: (response) => {
                    if (!response.response
                        || response.responseStatus === ResponseStatusType.Error
                    ) {
                        this.DisplayMessage(response.message, NotificationType.Error);
                    }

                    this.router.navigateByUrl('p/login');
                },
                error: error => {
                    this.DisplayMessage(error, NotificationType.Error);
                }
            });
    }

    public GoToLoginPage() {
        this.router.navigateByUrl('/p/login');
    }

    public IsFormValid() {
        return this.RegisterForm.valid;
    }
}