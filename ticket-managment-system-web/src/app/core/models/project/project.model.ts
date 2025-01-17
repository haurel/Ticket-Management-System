import { UserPermissionModel } from "..";
import { StatusType } from "../../enums";

export class ProjectModel {
    public projectId!: string;
    public organizationId!: string;
    public projectGuid!: string;
    public projectName!: string;
    public Users: UserPermissionModel[] = [];
    public SprintIds!: string;
    public statusId!: StatusType;

    constructor(project: Partial<ProjectModel> = {}) {
        Object.assign(this, project);
    }
}