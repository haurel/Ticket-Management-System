import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from "@angular/material/form-field";
import { LayoutPageComponent } from "./pages/layout/layout.page.component";
import { LoginPageComponent } from "./pages/login/login.page.component";
import { RegisterPageComponent } from "./pages/register/register.page.component";
import { PublicRoutingModule } from "./public.routing.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        PublicRoutingModule,
        MatFormFieldModule,
        MatButtonModule,
        FormsModule,
    ],

    declarations: [
        LayoutPageComponent,
        LoginPageComponent,
        RegisterPageComponent,
    ],
})
export class PublicModule { }