import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatGridListModule } from "@angular/material/grid-list";
import * as component from './index';

const components = [
    component.OrganizationComponent,
];

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatGridListModule,
        MatFormFieldModule,
        MatButtonModule,
        MatCardModule,
        FormsModule,
    ],

    declarations: components,
    exports: components
})
export class PrivateCompoenentModule { }