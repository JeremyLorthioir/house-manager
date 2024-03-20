import { Recurrence } from "./recurrence.interface";
import { UserTask } from "./userTask.interface";

export interface Task {
    id: string;
    name: string;
    type: string;
    recurrence: Recurrence;
    userTasks: UserTask[];
    createdAt: string;
    updatedAt: string;
    status: string;
    dueDate: Date;
}