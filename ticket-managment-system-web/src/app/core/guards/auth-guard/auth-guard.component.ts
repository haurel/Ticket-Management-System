import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { CoreService } from "../../../shared/services/core/core.service";


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(
        private router: Router,
        private coreService: CoreService) {
    }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
        const user = this.coreService.UserValue;
        if (user) {
            return true;
        }

        this.router.navigate(["p/login"], { queryParams: { returnUrl: state.url }});
        return false;
    }

}