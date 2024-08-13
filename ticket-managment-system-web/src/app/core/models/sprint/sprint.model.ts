import { SprintType } from "../../enums";
import { SprintTaskModel } from "./sprint-task.model";

export class SprintModel {
    public sprintId!: String;
    public sprintName!: String;
    public projectId!: String;
    public tasks!: SprintTaskModel[];
    public sprintType!: SprintType;
    public startDate!: Date;
    public endDate!: Date;

    constructor(sprint: Partial<SprintModel> = {}) {
        Object.assign(this, sprint);
    }
}