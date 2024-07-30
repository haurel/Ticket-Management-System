import { NotificationType } from "../../enums";

export class NotificationModel {
    public Message!: string;
    public NotificationType!: NotificationType;

    constructor(notification: Partial<NotificationModel> = {}) {
        Object.assign(this, notification);
    }
}