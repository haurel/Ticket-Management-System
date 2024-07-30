import { Injectable, Injector } from "@angular/core";
import { NotificationType } from "../../../core/enums";
import { NotificationModel, UserModel } from "../../../core/models";
import { NotificationService } from "../../services";
import { CoreService } from "../../services/core/core.service";
@Injectable({
    providedIn: 'root'
})
export class BaseComponent {
    public User!: UserModel;

    private notificationService: NotificationService;
    private coreService: CoreService;

    constructor(injector: Injector) {
        this.notificationService = injector.get(NotificationService);
        this.coreService = injector.get(CoreService);

        this.User = this.coreService.UserValue ?? new UserModel();
    }

    public DisplayMessage(message: string, notificationType: NotificationType = NotificationType.Success) {
        const notification = new NotificationModel({
            Message: message,
            NotificationType: notificationType,
        });
        this.notificationService.DisplayMessage(notification);
    }

    public CallApi(apiService: any, next: (value: any) => void) {
        apiService
            .subscribe({
            next: (next),
            error: () => {
                this.DisplayMessage("Something went wrong!", NotificationType.Error);
            }
        });
    }
}