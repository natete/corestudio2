import { Component, OnInit } from '@angular/core';
import { AuthService } from '../core/auth/auth.service';
import { Person } from '../core/auth/person';
import { SpinnerService } from '../core/spinner/spinner.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-home-page',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss']
})
export class ContainerComponent implements OnInit {

  person: Person;
  spinnerState: Observable<boolean>;

  constructor(private authService: AuthService,
              private spinnerService: SpinnerService) { }

  ngOnInit() {
    this.authService.getAccount()
        .subscribe(
            person => this.person = person
        );

    this.spinnerState = this.spinnerService.getSpinnerState();
  }
}
