import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import {MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [MatTableModule, CommonModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss'
})
export class TaskListComponent implements OnInit {
  httpClient = inject(HttpClient);
  tasks: any[] = [];
  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.httpClient.get("http://localhost:8080/tasks").subscribe((data: any) => { console.log(data); this.tasks = data; })
  }
}
