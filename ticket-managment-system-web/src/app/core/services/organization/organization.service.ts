import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from '@environments/environment';
import { ResponseModel } from "../../models";
import { OrganizationViewModel } from "../../view-models";

@Injectable({ providedIn: 'root' })
export class OrganizationService {
    constructor(private router: Router,
        private http: HttpClient)
    {
    }

    public GetOrganizations(request: String) {
        return this.http.get<ResponseModel<OrganizationViewModel[]>>(`${environment.apiUrl}organization/getOrganizations?userId=${request}`);
    }
}