import { TaskType } from "../../enums";
import { TaskUserDetailModel } from "../user";

export class SprintTaskModel {
    public taskId!: String;
    public description!: String;
    public taskType!: TaskType;
    public assignee: TaskUserDetailModel | null = null;
    public owner!: TaskUserDetailModel;


    constructor(sprintTask: Partial<SprintTaskModel> = {}) {
        Object.assign(this, sprintTask);
    }
}