import { Recurrence } from "./Recurrence.interface";
import { UserTask } from "./UserTask.interface";

export interface Task {
    id: string;
    name: string;
    type: string;
    recurrence: Recurrence;
    userTasks: UserTask[];
    createdAt: string;
    updatedAt: string;
    status: string;
}