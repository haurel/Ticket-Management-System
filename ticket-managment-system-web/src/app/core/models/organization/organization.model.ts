
export class OrganizationModel {
    public id: string | null = null;
    public name!: string;
    public createdDate: Date | null = null;
    public modifiedDate: Date | null = null;

    constructor(organization: Partial<OrganizationModel> = {}) {
        Object.assign(this, organization);
    }
}