import { Injectable } from '@angular/core';
import { Http, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { Client } from './client';

@Injectable()
export class ClientService {

  private readonly ENDPOINT = `${environment.baseUrl}/clients`;

  private currentPage = 0;
  private currentDirection = 'ASC';
  private currentSortBy;
  private size = 10;
  private q: string;

  constructor(private http: Http) { }

  getClients(sortBy?: string, direction?: string, q?: string): Observable<any> {
    this.initializeSearch(sortBy, direction, q);

    const options: RequestOptionsArgs = {};

    options.params = this.buildParams();

    return this.http.get(this.ENDPOINT, options)
               .map(res => this.buildResponse(res));
  }

  saveClient(client: Client): Observable<Client> {
    if (client.id) {
      return this.http.put(`${this.ENDPOINT}/${client.id}`, client)
                 .map((res: any) => res as Client);
    } else {
      return this.http.post(this.ENDPOINT, client)
                 .map((res: any) => res as Client);
    }
  }

  private initializeSearch(sortBy?: string, direction?: string, q?: string) {
    this.currentPage = 0;
    this.currentSortBy = sortBy || this.currentSortBy;
    this.currentDirection = direction || this.currentDirection;
    this.q = q;
  }

  private buildParams() {
    const params: { [key: string]: any } = {
      direction: this.currentDirection,
      page: this.currentPage,
      size: this.size,
      q: this.q
    };

    if (this.currentSortBy) {
      params.sortBy = this.currentSortBy;
    }

    return params;
  }

  private buildResponse(res: any): any {
    return Object.assign({}, res, { content: res.content.map(item => this.mapItemToClass(item)) });
  }

  private mapItemToClass(item: any): Client {
    item.photo = item.photo || '/assets/images/face.png';
    return item as Client;
  }
}
