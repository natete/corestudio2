import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { environment } from '../../environments/environment';
import { TokenService } from '../core/auth/token.service';
import { AuthService } from '../core/auth/auth.service';

@Injectable()
export class LoginService {

  constructor(private http: Http,
              private authService: AuthService,
              private tokenService: TokenService) { }

  login(username, password): Observable<void> {

    return this.http.post(`${environment.baseUrl}/auth/login`, { username, password })
               .map(res => this.handleAuthResponse(res));
  }

  private handleAuthResponse(authInfo: any): void {
    this.tokenService.setAuthInfo(authInfo);
  }
}
