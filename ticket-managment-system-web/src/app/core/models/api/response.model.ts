import { ResponseStatusType } from "../../enums";

export class ResponseModel<T> {
    public response: T | null = null;
    public message!: string;
    public responseStatus: ResponseStatusType = ResponseStatusType.Error;

    constructor(user: Partial<ResponseModel<T>>) {
        Object.assign(user);
    }
}