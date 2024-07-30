import { Component, Injector, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute } from "@angular/router";
import { OrganizationService } from "../../../core/services";
import { OrganizationProjectViewModel } from "../../../core/view-models";
import { BasePage } from "../../../shared/pages";

@Component({
    templateUrl: './organization.page.component.html'
})
export class OrganizationPageComponent extends BasePage implements OnInit {
    public Organization!: OrganizationProjectViewModel;
    public Columns: string[] = ['ProjectName', 'Actions'];
    public DataSource!: MatTableDataSource<any>;

    constructor(injector: Injector,
        route: ActivatedRoute,
        private organizationService: OrganizationService,
    ) {
        super(injector);
        this.Organization = route.snapshot.data['data'].Response;
    }

    public ngOnInit(): void {
        this.DataSource = new MatTableDataSource(this.Organization.projects);
    }
}