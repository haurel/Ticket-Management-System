import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router } from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { NotificationService } from '../../../shared/services';
import { NotificationType, ResponseStatusType } from '../../enums';
import { NotificationModel, ResponseModel } from '../../models';
import { ProjectService } from '../../services';
import { OrganizationProjectViewModel } from '../../view-models';
 
@Injectable({
  providedIn: 'root'
})
export class ProjectResolverService implements Resolve<ResponseModel<OrganizationProjectViewModel> | null> {

  constructor(private router: Router,
    private projectService: ProjectService,
    private notificationService: NotificationService
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ResponseModel<OrganizationProjectViewModel> | null> {
    const organizationId = route.queryParams['id'];
    return this.projectService.GetOrganizationProjects(organizationId)
        .pipe(
            map(response => {
                if (!response.response
                    || response.responseStatus == ResponseStatusType.Error
                ) {
                    this.notificationService.DisplayMessage(new NotificationModel({Message: response.message, NotificationType: NotificationType.Error}));
                    this.router.navigateByUrl('/');
                    return null;
                } else {
                    return response;
                }
            }),
            catchError(error => {
            this.notificationService.DisplayMessage(new NotificationModel({ Message: error, NotificationType: NotificationType.Error }))
            this.router.navigate(['/']);
            return of(null);
        }));
  }
}