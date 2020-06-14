import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from 'src/app/models/user.model';
import { TokenStorageService } from './token-storage.service';
import { Config } from '../config';
import { UserCredentials } from 'src/app/models/user-credentials.model';
import { Token } from 'src/app/models/token.model';
import * as jwtDecode from 'jwt-decode';
import { Role } from 'src/app/models/role.model';



@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient, private tokenStore: TokenStorageService) {
    this.currentUserSubject = new BehaviorSubject<User>(this.decodeUser());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(data: UserCredentials) {
    const user = Config.params.username + data.username;
    const password = Config.params.password + data.password;
    const header = Config.grantType.password + user + password + Config.clientId;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded)
      .pipe(map((token: Token) => {
        if (token) {
          this.tokenStore.saveToken(token);
        }
      }));
  }

  public refreshToken(token: Token) {
    const header = Config.grantType.refresh + Config.clientId + Config.refreshToken + token.refreshToken;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded);
  }

  decodeUser() {
    if (this.tokenStore.checkToken()) {
      var token = this.tokenStore.getToken();
      var decoded = jwtDecode(token.accessToken);
      return new User(decoded.authorities, decoded.user_name, decoded.userId);
    }
    return null;
  }

  logout() {
    this.tokenStore.clearToken();
    window.location.reload()
  }

  isAuthenticated(): boolean {
    return this.isUser() || this.isAdmin();
  }

  isAdmin(): boolean {
    return this.currentUserValue() && this.currentUserValue().authorities.includes(Role.ROLE_ADMIN);
  }

  isUser() {
    return this.currentUserValue() && this.currentUserValue().authorities.includes(Role.ROLE_USER);
  }
}
