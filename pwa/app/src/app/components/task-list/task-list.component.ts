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
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss'
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];

  constructor(private TaskService: TaskService) { }

  ngOnInit(): void {
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
}
