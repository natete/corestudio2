import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) { }

  canActivate(next: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (state.url.startsWith('/login')) {
      return this.canActivateLogin();
    } else {
      return this.canActivateAuthorizedUrl();
    }
  }

  private canActivateLogin(): boolean {
    const isAuthenticated = this.authService.isAuthenticated();

    if (isAuthenticated) {
      this.router.navigate(['/']);
    }

    return !isAuthenticated;
  }

  private canActivateAuthorizedUrl() {
    const isAuthenticated = this.authService.isAuthenticated();

    if (!isAuthenticated) {
      this.router.navigate(['/login']);
    }

    return isAuthenticated;
  }
}
