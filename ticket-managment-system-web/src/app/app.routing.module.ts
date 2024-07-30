import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth-guard/auth-guard.component';


export const routes: Routes = [
    { path: "",  loadChildren: () => import('./private/pages/private.routing.module').then((p) => p.PrivateRoutingModule), canActivate: [ AuthGuard ] },
    { path: 'p', loadChildren: () => import('./public/public.module').then((p) => p.PublicModule) },


    { path: '**', redirectTo: '' }
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }