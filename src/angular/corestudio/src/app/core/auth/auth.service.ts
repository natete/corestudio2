import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Person } from './person';
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthService {

  constructor(private http: Http,
              private tokenService: TokenService) { }

  isAuthenticated(): boolean {
    return !!this.tokenService.getAccessToken();
  }

  getAccount(): Observable<Person> {
    return this.http.get(`${environment.baseUrl}/auth/account`)
        .map(rawPerson => new Person(rawPerson));
  }
}
