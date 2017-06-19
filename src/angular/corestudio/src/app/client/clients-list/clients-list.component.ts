import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.scss']
})
export class ClientsListComponent implements OnInit {
  headers: string[];
  clients: any[];


  constructor(private clientService: ClientService) {
    this.headers = this.buildHeaders();
  }

  ngOnInit() {

    this.clientService.getClients()
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
}
