import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from '@angular/material/icon';
import { MatColumnDef, MatRowDef, MatTableModule } from "@angular/material/table";
import { RouterOutlet } from "@angular/router";
import { PrivateCompoenentModule } from "../component/private.component.module";
import * as page from './index';

const components = [
    page.HomePageComponent,
    page.OrganizationPageComponent,
];

@NgModule({
    imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatRowDef,
    MatColumnDef,
    MatTableModule,
    MatIconModule,
    FormsModule,
    PrivateCompoenentModule,
    RouterOutlet
],

    declarations: components,
    exports: components
})
export class PrivatePageModule { }