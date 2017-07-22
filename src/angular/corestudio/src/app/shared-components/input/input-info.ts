import { InputError } from './input-error';
export interface InputInfo {
  type: string;
  formControlName: string;
  placeholder: string;
  suffix?: string;
  autocomplete?: string;
  errors: InputError[];
}