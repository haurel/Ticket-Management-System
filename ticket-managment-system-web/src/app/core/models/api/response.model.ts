import { HttpStatusCode } from "@angular/common/http";
import { ResponseStatusType } from "../../enums";

export class ResponseModel<T> {
    public Response: T | null = null;
    public Message!: string;
    public ResponseStatus: ResponseStatusType = ResponseStatusType.Error;
    public HttpStatus: HttpStatusCode = HttpStatusCode.NotFound;

    constructor(user: Partial<ResponseModel<T>>) {
        Object.assign(user);
    }
}