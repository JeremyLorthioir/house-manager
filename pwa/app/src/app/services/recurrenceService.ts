import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Recurrence } from '../interfaces/recurrence.interface';

@Injectable({
    providedIn: 'root'
})
export class RecurrenceService {
    private apiUrl: string = environment.apiUrl;
    constructor(private http: HttpClient) { }

    getRecurrences(): Observable<Recurrence[]> {
        return this.http.get<Recurrence[]>(`${this.apiUrl}/recurrences`);
    }
}