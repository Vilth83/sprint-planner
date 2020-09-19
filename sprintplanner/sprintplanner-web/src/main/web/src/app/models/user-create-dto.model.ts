export class UserCreateDto {
  username: string;
  password: string;
  firstname: string;
  lastname: string;

  constructor(username:string, password:string, firstname: string, lastname: string) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }
}
