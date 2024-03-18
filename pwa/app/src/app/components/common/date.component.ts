import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-common-date',
    standalone: true,
    imports: [],
    template: '<span>{{ formatDate(inputDate) }}</span>'
})
export class DateComponent {
    @Input() inputDate!: Date;

    formatDate(date: Date): string {
        date = new Date(date);

        const day = date.getDate();
        const month = date.getMonth() + 1;
        const year = date.getFullYear();

        const formattedDay = day < 10 ? '0' + day : day;
        const formattedMonth = month < 10 ? '0' + month : month;

        return `${formattedDay}/${formattedMonth}/${year}`;
    }
}
