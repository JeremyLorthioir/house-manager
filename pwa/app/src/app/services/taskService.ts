import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Task } from '../interfaces/task.interface';
import { environment } from '../../environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class TaskService {
    private apiUrl: string = environment.apiUrl;

    constructor(private http: HttpClient) { }

    getTasks(): Observable<Task[]> {
        return this.http.get<Task[]>(`${this.apiUrl}/tasks`);
    }
}