import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from '@environments/environment';
import { ResponseModel, SprintModel } from "../../models";

@Injectable({ providedIn: 'root' })
export class SprintService {
    constructor(private http: HttpClient) {}

    public GetSprints(request: String) {
        return this.http.get<ResponseModel<SprintModel[]>>(`${environment.apiUrl}sprint/getSprints?userId=${request}`);
    }
}