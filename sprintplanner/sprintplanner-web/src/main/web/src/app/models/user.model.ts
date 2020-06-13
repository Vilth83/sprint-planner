import { Role } from './role.model';

export class User {
  authorities: Role[];
  username: string;
  userId: string;

  constructor(authorities: Role[], username:string, userId:string) {
    this.authorities = authorities;
    this.userId = userId;
    this.username = username;
  }

  hasRole(roles: Role[]): boolean {
    return this.authorities.some(role => roles.includes(role));
  }
}
