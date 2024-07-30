import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from '@environments/environment';
import { ResponseModel } from "../../models";
import { OrganizationProjectViewModel } from "../../view-models";

@Injectable({ providedIn: 'root' })
export class ProjectService {
    constructor(private router: Router,
        private http: HttpClient)
    {
    }

    public GetOrganizationProjects(organizationId: String) {
        return this.http.get<ResponseModel<OrganizationProjectViewModel>>(`${environment.apiUrl}project/getOrganizationProjects?organizationId=${organizationId}`);
    }
}