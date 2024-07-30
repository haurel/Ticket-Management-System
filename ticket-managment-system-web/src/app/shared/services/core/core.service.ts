import { Injectable, Injector } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { StorageService } from "..";
import { UserModel } from "../../../core/models";
@Injectable({
    providedIn: 'root'
})
export class CoreService {
    public User!: Observable<UserModel | null>;

    private storageService: StorageService;
    private userSubject!: BehaviorSubject<UserModel | null>;
    
    public get UserValue() {
        return this.userSubject.value;
    }
   
    constructor(injector: Injector) {
        this.storageService = injector.get(StorageService);
        this.userSubject = new BehaviorSubject(JSON.parse(this.storageService.Get('user')!));
        this.User = this.userSubject.asObservable();

        const userJson = this.storageService.Get("user");
        if (!userJson) {
            //TODO 
        } else {
            this.User = JSON.parse(userJson);
        }
    }
}