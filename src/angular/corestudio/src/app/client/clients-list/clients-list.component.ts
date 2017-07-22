import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../client';
import { TableDefinition } from '../../shared-components/table/table-definition';
import { FieldDefinition } from '../../shared-components/table/field-definition';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.scss']
})
export class ClientsListComponent implements OnInit {
  tableDefinition: TableDefinition;
  clients: Client[];
  private searchSubscription: Subscription;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private clientService: ClientService) {
    this.tableDefinition = this.buildTableDefinition();
  }

  ngOnInit() {

    this.searchSubscription = this.clientService.getClients()
                                  .subscribe(
                                      response => this.handleResponse(response)
                                  );
  }

  goToNewClient() {
    this.router.navigate(['./new'], { relativeTo: this.route });
  }

  search(searchTerm) {
    this.searchSubscription.unsubscribe();

    this.searchSubscription = this.clientService.getClients(null, null, searchTerm)
                                  .subscribe(
                                      response => this.handleResponse(response)
                                  );
  }

  private buildHeaders(): string[] {
    return ['', 'Nombre', 'Clases Pendientes', 'Fecha fin de bono'];
  }

  private handleResponse(response: any) {
    this.clients = response.content;
  }

  private buildFields(): FieldDefinition[] {
    return [
      { fieldName: 'photo', fieldType: 'image', fieldAlignment: 'center' },
      { fieldName: 'fullName', fieldType: 'text' },
      { fieldName: 'pendingSessions', fieldType: 'text' },
      { fieldName: 'lastDate', fieldType: 'text' }
    ];
  }

  private buildTableDefinition(): TableDefinition {
    return {
      headers: this.buildHeaders(),
      fields: this.buildFields()
    }
  }
}
