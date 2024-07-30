import { OrganizationModel, ProjectModel } from "../../models";

export class OrganizationProjectViewModel {
    public organization!: OrganizationModel;
    public projects: ProjectModel[] = [];

    constructor(organization: Partial<OrganizationProjectViewModel> = {}) {
        Object.assign(this, organization);
    }
}