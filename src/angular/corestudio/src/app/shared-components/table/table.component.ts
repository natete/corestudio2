import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TableDefinition } from './table-definition';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  @Input() tableDefinition: TableDefinition;
  @Input() items: any[];
  searchSubject = new Subject<string>();
  @Output() onSearch = new EventEmitter<string>();
  searchTerm: string;

  constructor() { }

  ngOnInit() {
    this.searchSubject.asObservable().debounceTime(500).subscribe(() => this.onSearch.emit(this.searchTerm));
  }

  onTextChange() {
    this.searchSubject.next(this.searchTerm);
  }
}
