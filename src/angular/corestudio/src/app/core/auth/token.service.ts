import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  private readonly AUTH_INFO_KEY = 'corestudio-auth';

  constructor() { }

  getAccessToken(): any {
    const authInfo = localStorage.getItem(this.AUTH_INFO_KEY);
    return authInfo ? JSON.parse(authInfo).token : null;
  }

  setAuthInfo(authInfo: any = {}) {
    localStorage.setItem(this.AUTH_INFO_KEY, JSON.stringify(authInfo));
  }

  getRefreshToken() {
    const authInfo = localStorage.getItem(this.AUTH_INFO_KEY);
    return authInfo ? JSON.parse(authInfo).refreshToken : null;
  }

  clearToken() {
    localStorage.removeItem(this.AUTH_INFO_KEY);
  }

  setAccessToken(token: string = '') {
    const rawAuthInfo = localStorage.getItem(this.AUTH_INFO_KEY);
    const authInfo = rawAuthInfo && JSON.parse(rawAuthInfo);
    authInfo.token = token;
    this.setAuthInfo(authInfo)
  }
}
