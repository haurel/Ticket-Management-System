export class LoginUserRequestModel {
    public Username!: string;
    public Password!: string;

    constructor(loginUserRequest: Partial<LoginUserRequestModel> = {}) {
        Object.assign(this, loginUserRequest);
    }
}