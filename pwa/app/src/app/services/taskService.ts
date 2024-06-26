import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Task } from '../interfaces/task.interface';
import { environment } from '../../environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class TaskService {
    private apiUrl: string = environment.apiUrl;
    constructor(private http: HttpClient) { }

    getTasks(): Observable<Task[]> {
        return this.http.get<Task[]>(`${this.apiUrl}/tasks`);
    }

    createTask(task: any): Observable<Task> {
        return this.http.post<Task>(`${this.apiUrl}/tasks`, task, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) });
    }

    getTaskTypes(): Observable<string[]> {
        return this.http.get<string[]>(`${this.apiUrl}/task_types`);
    }

    validateTask(taskId: string, userId: string) {
        var data = { taskId: taskId, userId: userId };
        return this.http.post<string[]>(`${this.apiUrl}/user_tasks`, data, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) });
    }

    deleteTask(taskId: string): any {
        return this.http.delete(`${this.apiUrl}/tasks/${taskId}`);
    }
}