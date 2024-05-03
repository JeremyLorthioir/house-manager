import { Component } from '@angular/core';
import { TaskService } from '../../services/taskService';
import { Task } from '../../interfaces/task.interface';
import { CommonModule } from '@angular/common';
import { DateComponent } from '../common/date.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, DateComponent],
  templateUrl: './home.component.html'
})
export class HomeComponent {
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  taskTypes: String[] = [];

  constructor(private TaskService: TaskService) { }

  ngOnInit(): void {
    this.initTasks();
    this.initTaskTypes();
  }

  initTasks(): void {
    this.TaskService.getTasks().subscribe((tasks) => { this.tasks = tasks, this.filteredTasks = tasks });
  }

  initTaskTypes(): void {
    this.TaskService.getTaskTypes().subscribe((types) => { this.taskTypes = types });
  }

  getTasksByTypeStatus(taskType: String, status: String): Task[] {
    return this.tasks.filter((task) => task.type === taskType && task.status === status);
  }

  filterTasksByType(taskType: String): void {
    this.filteredTasks = this.tasks.filter((task) => task.type === taskType);
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
