import { Component, Injector, OnInit } from "@angular/core";
import { BasePage } from "../../../shared/pages";

@Component({
    templateUrl: './home.page.component.html'
})
export class HomePageComponent extends BasePage implements OnInit {

    constructor(injector: Injector,
    ) {
        super(injector);
    }

    public ngOnInit(): void {
        
    }
}