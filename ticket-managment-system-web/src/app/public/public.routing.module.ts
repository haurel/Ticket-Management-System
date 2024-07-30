import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LayoutPageComponent } from "./pages/layout/layout.page.component";
import { LoginPageComponent } from "./pages/login/login.page.component";
import { RegisterPageComponent } from "./pages/register/register.page.component";


const routes: Routes = [
    {
        path: '', component: LayoutPageComponent,
        children: [
            {
                path: 'login',
                component: LoginPageComponent
            },
            {
                path: 'register',
                component: RegisterPageComponent
            }
        ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PublicRoutingModule { }