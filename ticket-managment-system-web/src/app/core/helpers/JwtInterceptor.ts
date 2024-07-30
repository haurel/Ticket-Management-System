import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { CoreService } from '../../shared/services';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    private coreSerivce: CoreService

    constructor(injector: Injector) {
        this.coreSerivce = injector.get(CoreService);
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const user = this.coreSerivce.UserValue;
        const isLoggedIn = user && user.Token;
        const isApiUrl = request.url.startsWith(environment.apiUrl);
        
        if (isLoggedIn && isApiUrl) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${user.Token}`
                }
            });
        }

        return next.handle(request);
    }
}