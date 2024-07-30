import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProjectResolverService } from "../../core/resolver";
import { HomePageComponent } from "./home/home.page.component";
import { OrganizationPageComponent } from "./organization/organization.page.component";


const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: '',
                pathMatch: 'full',
                component: HomePageComponent
            },
            {
                path: ':id',
                component: OrganizationPageComponent,
                resolve: {
                    data: ProjectResolverService
                }
            }
        ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PrivateRoutingModule { }