import { Injector } from "@angular/core";
import { BaseComponent } from "../../components";

export class BasePage extends BaseComponent {

    constructor(injector: Injector) {
        super(injector);
    }
}