export class UserModel {
    public UserId!: string;
    public UserName!: string;
    public EmailAddress!: string;
    public Token!: string;

    constructor(user: Partial<UserModel> = {}) {
        Object.assign(user);
    }
}