import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { Subject } from "rxjs";
import { NotificationType } from "../../../core/enums";
import { NotificationModel } from "../../../core/models";

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    private notificationSubject: Subject<NotificationModel> = new Subject<NotificationModel>();

    constructor(private toastrService: ToastrService) {
        this.notificationSubject.subscribe(message => {
            switch(message.NotificationType) {
                case NotificationType.Success:
                    this.toastrService.success(message.Message);
                    break;
                case NotificationType.Warning:
                    this.toastrService.warning(message.Message);
                    break;
                case NotificationType.Error:
                    this.toastrService.error(message.Message);
                    break;
                case NotificationType.Info:
                    this.toastrService.info(message.Message);
                    break;
            }
        });
    }

    public DisplayMessage(message: NotificationModel) {
        this.notificationSubject.next(message);
    }
}