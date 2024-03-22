import { Routes } from '@angular/router';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskCreateComponent } from './task-create/task-create.component';

export const routes: Routes = [
    { path: 'task-list', component: TaskListComponent },
    { path: 'task-create', component: TaskCreateComponent },
];
