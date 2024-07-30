import { OrganizationDetailModel, OrganizationModel } from "../../models";

export class OrganizationViewModel {
    public organization!: OrganizationModel;
    public organizationDetail!: OrganizationDetailModel;

    constructor(organization: Partial<OrganizationModel> = {}) {
        Object.assign(this, organization);
    }
}