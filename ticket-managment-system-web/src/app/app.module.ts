import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ToastrModule } from "ngx-toastr";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app.routing.module";
import { AuthGuard } from "./core/guards/auth-guard/auth-guard.component";
import { JwtInterceptor } from "./core/helpers/JwtInterceptor";
import { PrivateCompoenentModule } from "./private/component/private.component.module";
import { PrivatePageModule } from "./private/pages/page.module";
import { NavbarComponent } from './shared/components/navbar/navbar.component';

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        AppRoutingModule,
        ToastrModule.forRoot({
            positionClass :'toast-bottom-right'
        }),
        BrowserAnimationsModule,
        MatFormFieldModule,
        PrivateCompoenentModule,
        PrivatePageModule,
    ],

    declarations: [
        AppComponent,
        NavbarComponent,
    ],
    providers: [
        AuthGuard,
        provideHttpClient(withInterceptorsFromDi()),
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    ],
    bootstrap: [AppComponent],
})
export class AppModule { }