export class JwtUserModel {
    public UserId!: string;
    public Username!: string;
    public Token: string | null = null;

    constructor(user: Partial<JwtUserModel> = {}) {
        Object.assign(this, user);
    }
}