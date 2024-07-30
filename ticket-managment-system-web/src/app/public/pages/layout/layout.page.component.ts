import { Component } from "@angular/core";
import { Router } from "@angular/router";


@Component({
    templateUrl: 'layout.page.component.html',
})
export class LayoutPageComponent {

    constructor(private router: Router) {
    }
}