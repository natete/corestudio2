import { RegisteredUser } from './registered-user';

export class Client {
  id?: number;
  name: string;
  firstSurname: string;
  secondSurname?: string;
  address?: string;
  firstPhone: string;
  secondPhone?: string;
  email?: string;
  admissionDate: Date;
  birthdate: Date;
  photo: string;
  isActive: boolean;
  registeredUser: RegisteredUser;
}