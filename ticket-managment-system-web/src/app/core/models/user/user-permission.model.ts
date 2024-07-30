import { UserRoleType } from "../../enums";

export class UserPermissionModel {
    public userId!: string;
    public userRoleType!: UserRoleType;

    constructor(userPermission: Partial<UserPermissionModel> = {}) {
        Object.assign(this, userPermission);
    }
}