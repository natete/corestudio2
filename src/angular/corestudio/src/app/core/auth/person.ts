export class Person {
  username: string;
  role: string;
  name: string;
  firstSurname: string;
  secondSurname: string;


  constructor(rawPerson: any) {
    if (rawPerson) {
      this.username = rawPerson.registeredUser.username;
      this.role = rawPerson.registeredUser.role;
      this.name = rawPerson.name;
      this.firstSurname = rawPerson.firstSurname;
      this.secondSurname = rawPerson.secondSurname;
    }
  }

  getFullName(): string {
    return `${this.name} ${this.firstSurname}`;
  }
}
