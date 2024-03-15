import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { Task } from '../interfaces/task.interface';
import { TaskService } from '../services/taskService';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [MatTableModule, CommonModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss'
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];

  constructor(private TaskService: TaskService) { }

  ngOnInit(): void {
    this.TaskService.getTasks().subscribe((tasks) => { this.tasks = tasks });
  }
}
