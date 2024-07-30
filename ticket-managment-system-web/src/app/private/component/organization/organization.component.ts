import { Component, Injector, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NotificationType, ResponseStatusType } from "../../../core/enums";
import { OrganizationService } from "../../../core/services/organization";
import { OrganizationViewModel } from "../../../core/view-models";
import { BaseComponent } from "../../../shared/components";

@Component({
    selector: 'organization',
    templateUrl: './organization.component.html',
    styleUrls: ['./organization.component.scss'],
})
export class OrganizationComponent extends BaseComponent implements OnInit {
    public OrganizationDetails: OrganizationViewModel[] = [];
    
    constructor(injector: Injector,
        private router: Router,
        private organizationService: OrganizationService
    ) {
        super(injector);
    }

    public ngOnInit(): void {
        this.CallApi(
            this.organizationService.GetOrganizations(this.User.UserId),
            (response) => {
                if (!response.Response
                    || response.ResponseStatus == ResponseStatusType.Error
                ) {
                    this.DisplayMessage(response.Message, NotificationType.Error);
                } else {
                    this.OrganizationDetails = response.Response;
                }
            }
        )
    }

    public NavigateTo(organizationId: string) {
        this.router.navigate(['/organization'], {queryParams: {id: organizationId }});
    }
}