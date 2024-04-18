import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { Task } from '../../interfaces/task.interface';
import { TaskService } from '../../services/taskService';
import { DateComponent } from '../common/date.component';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [MatTableModule, CommonModule, DateComponent],
  templateUrl: './task-list.component.html'
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];

  constructor(private TaskService: TaskService) { }

  ngOnInit(): void {
    this.initTasks();
  }

  initTasks(): void {
    this.TaskService.getTasks().subscribe((tasks) => { this.tasks = tasks });
  }

  mapStatusToClass(status: String): string {
    switch (status) {
      case "expired":
        return "bg-danger";
      case "pending":
        return "bg-warning";
      default:
        return "bg-success";
    }
  }

  validateTask(task: Task) {
    var userId: string = "ecb3d479-42ce-42cd-9e1b-cd5f7f94fcf2";
    this.TaskService.validateTask(task.id, userId).subscribe({
      next: () => {
        this.initTasks();
      },
      error: (error) => {
        console.log(error);
        alert("Impossible de valider la tâche.");
      }
    });
  }
}
