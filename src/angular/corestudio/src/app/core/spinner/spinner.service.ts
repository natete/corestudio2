import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class SpinnerService {

  private spinnerState = new BehaviorSubject<boolean>(false);

  constructor() { }

  getSpinnerState(): Observable<boolean> {
    return this.spinnerState.asObservable();
  }

  startSpinner() {
    this.spinnerState.next(true);
  }

  stopSpinner() {
    this.spinnerState.next(false);
  }
}
