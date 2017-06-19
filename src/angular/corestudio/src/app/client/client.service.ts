import { Injectable } from '@angular/core';
import { Http, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

@Injectable()
export class ClientService {

  private readonly GET_ALL_ENDPOINT = 'clients';

  private currentPage = 0;
  private currentDirection = 'ASC';
  private currentSortBy;
  private size = 10;

  constructor(private http: Http) { }

  getClients(sortBy?: string, direction?: string): Observable<any> {
    this.initializeSearch(sortBy, direction);

    const options: RequestOptionsArgs = {};

    options.params = this.buildParams();

    return this.http.get(`${environment.baseUrl}/${this.GET_ALL_ENDPOINT}`, options)
               .map(res => this.buildResponse(res));
  }

  private initializeSearch(sortBy?: string, direction?: string) {
    this.currentPage = 0;
    this.currentSortBy = sortBy || this.currentSortBy;
    this.currentDirection = direction || this.currentDirection;
  }

  private buildParams() {
    const params: { [key: string]: any } = {
      direction: this.currentDirection,
      page: this.currentPage,
      size: this.size
    };

    if (this.currentSortBy) {
      params.sortBy = this.currentSortBy;
    }

    return params;
  }

  private buildResponse(res: any): any {
    return Object.assign({}, res, { content: res.content.map(item => this.mapItemToClass(item)) });
  }

  private mapItemToClass(item: any) {
    return item;
  }
}
