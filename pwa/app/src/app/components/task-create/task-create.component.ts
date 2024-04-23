import { Component, ElementRef, OnDestroy, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { TaskService } from '../../services/taskService';
import { RecurrenceService } from '../../services/recurrenceService';
import { Recurrence } from '../../interfaces/recurrence.interface';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-task-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './task-create.component.html'
})
export class TaskCreateComponent implements OnInit, OnDestroy {
  taskForm: FormGroup;
  taskTypes: string[] = [];
  recurrences: Recurrence[] = [];
  private subscriptions: Subscription = new Subscription();

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
    if (this.taskForm.valid) {
      const subscription = this.taskService.createTask(this.taskForm.value).subscribe({
        error: (error) => {
          console.log(error);
          alert("Impossible de créer la tâche.");
        }
      });

      const modalInstance = bootstrap.Modal.getInstance(document.getElementById("taskFormModal")!);
      modalInstance?.hide();
      this.subscriptions.add(subscription);
    }
  }

  getTaskTypes(): void {
    const subscription = this.taskService.getTaskTypes().subscribe(types => this.taskTypes = types);
    this.subscriptions.add(subscription);
  }

  getAllRecurrences(): void {
    const subscription = this.recurrenceService.getRecurrences().subscribe(recurrences => this.recurrences = recurrences);
    this.subscriptions.add(subscription);
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }
}
