import { Component } from '@angular/core';
import { TaskService } from '../../services/taskService';
import { Task } from '../../interfaces/task.interface';

@Component({
  selector: 'app-task-create',
  standalone: true,
  imports: [],
  templateUrl: './task-create.component.html'
})
export class TaskCreateComponent {
  constructor(private TaskService: TaskService) { }

  ngOnInit(): void {
    this.TaskService.createTask({
      "name": "Nettoyer toilettes",
      "type": "MENAGE",
      "recurrence": {
        "id": "7dc4ff7e-25cb-4126-a3ab-344f04bad038",
        "name": "Two_times_Week",
        "description": "Deux fois par semaine",
        "period": "SEMAINE",
        "frequency": 2
      }
    }).subscribe((task) => { console.log(task) });
  }
}
