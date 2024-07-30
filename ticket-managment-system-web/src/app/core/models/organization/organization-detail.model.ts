import { UserRoleType } from "../../enums";

export class OrganizationDetailModel {
    public organizationId!: string;
    public organizationUserRoleType!: UserRoleType;

    constructor(organizationDetail: Partial<OrganizationDetailModel> = {}) {
        Object.assign(this, organizationDetail);
    }
}