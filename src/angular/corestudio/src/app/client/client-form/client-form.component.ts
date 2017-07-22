import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { InputInfo } from '../../shared-components/input/input-info';
import { InputError } from '../../shared-components/input/input-error';
import { ClientFormDefinition } from './client-form.definition';
import { Client } from '../client';
import { Location } from '@angular/common';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {

  @Input() client: Client;
  userForm: FormGroup;
  loading: boolean;
  formFields: InputInfo[] = ClientFormDefinition;

  constructor(private fb: FormBuilder,
              private location: Location,
              private clientService: ClientService) { }

  ngOnInit() {
    this.client = this.client || new Client();
    const formControls = this.getFormControls();
    this.userForm = this.fb.group(formControls);
    this.userForm.get('isActive').setValue(true);
  }

  onSubmit() {
    this.clientService.saveClient(this.userForm.getRawValue())
        .subscribe(this.location.back, console.error);
  }

  cancel() {
    this.location.back();
  }

  getFormControls(): { [key: string]: any } {
    return this.formFields.reduce((acc, formField) => Object.assign({}, acc, this.mapToFormControl(formField)), {});
  }

  private mapToFormControl(formField: InputInfo): { [key: string]: any } {
    const result: { [key: string]: any } = {};

    result[formField.formControlName] = ['', this.mapErrorsToValidators(formField.errors)];

    return result;
  }

  private mapErrorsToValidators(errors: InputError[]): ValidationErrors[] {
    return errors.map(error => typeof error.val === 'undefined' ? Validators[error.type] : Validators[error.type](error.val));
  }
}
