import { RequestOptions, XHRBackend } from '@angular/http';
import { HttpService } from './http.service';
import { TokenService } from '../auth/token.service';
import { SpinnerService } from '../spinner/spinner.service';
import { Router } from '@angular/router';

export function httpFactory(backend: XHRBackend,
                            options: RequestOptions,
                            router: Router,
                            token: TokenService,
                            spinnerService: SpinnerService) {

  return new HttpService(backend, options, router, token, spinnerService);
}