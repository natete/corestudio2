import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  login: FormGroup;
  loading: boolean;
  error: string;

  constructor(private fb: FormBuilder,
              private router: Router,
              private loginService: LoginService) { }

  ngOnInit() {
    this.login = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit({ value, valid }: { value: any, valid: boolean }) {
    if (valid) {
      this.loading = true;
      this.error = null;

      this.loginService.login(value.username, value.password)
          .subscribe(
              () => this.handleLoginSuccess(),
              (res) => this.handleLoginError(res.json())
          );
    }
  }

  private handleLoginSuccess() {
    this.loading = false;
    this.router.navigate(['/']);
  }

  private handleLoginError(res) {
    this.loading = false;
    this.error = res.message;
  }
}
