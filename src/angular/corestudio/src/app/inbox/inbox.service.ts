import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { Message } from './model/message';

@Injectable()
export class InboxService {

  constructor(private http: Http) { }

  getMessages(): Observable<Message[]> {
    return this.http.get(`${environment.baseUrl}/messages`)
        .map((messages: any) => messages.map(rawMessage => new Message(rawMessage)));
  }

}
