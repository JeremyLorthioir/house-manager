import { Component } from '@angular/core';
import { TaskService } from '../../services/taskService';
import { Task } from '../../interfaces/task.interface';
import { RecurrenceService } from '../../services/recurrenceService';
import { Recurrence } from '../../interfaces/recurrence.interface';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-task-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './task-create.component.html'
})
export class TaskCreateComponent {
  taskForm: FormGroup;
  taskTypes: string[] = [];
  recurrences: Recurrence[] = [];

  constructor(private formBuilder: FormBuilder, private taskService: TaskService, private recurrenceService: RecurrenceService) { }

  ngOnInit(): void {
    this.getTaskTypes();
    this.getAllRecurrences();
    this.initForm();
  }

  private initForm(): void {
    this.taskForm = this.formBuilder.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      recurrenceId: ['', Validators.required]
    });
  }

  onSubmit() {
    console.log(this.taskForm.value);
    if (this.taskForm.valid) {
      this.taskService.createTask(this.taskForm.value).subscribe({
        next: () => {
          console.log("Ca marche")
        },
        error: (error) => {
          console.log(error);
          alert("Impossible de créer la tâche.");
        }
      });
    }
  }

  getTaskTypes(): void {
    this.taskService.getTaskTypes().subscribe(types => this.taskTypes = types);
  }

  getAllRecurrences(): void {
    this.recurrenceService.getRecurrences().subscribe(recurrences => this.recurrences = recurrences);
  }
}
