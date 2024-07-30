export class RegisterUserRequestModel {
    public Username!: string;
    public Password!: string;
    public EmailAddress!: string;

    constructor(registerUserRequest: Partial<RegisterUserRequestModel> = {}) {
        Object.assign(this, registerUserRequest);
    }
}