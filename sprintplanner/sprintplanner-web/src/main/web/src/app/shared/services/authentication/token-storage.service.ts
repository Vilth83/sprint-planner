import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { Config } from './../config';
import { Token } from 'src/app/models/token.model';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class TokenStorageService {

  constructor(public http: HttpClient) { }

  private fieldName = Config.token.cookieFieldName;

  saveToken(inputs: any) {
    const token: Token = this.mapToken(inputs);
    if (!!token) {
      Cookie.set(this.fieldName, JSON.stringify(token), 1);
    }
  }

  checkToken(): boolean {
    return Cookie.check(this.fieldName);
  }


  getToken(): Token {
    return this.checkToken()
      ? JSON.parse(Cookie.get(Config.token.cookieFieldName))
      : null
  }

  clearToken(): void {
    return Cookie.delete(this.fieldName);
  }

  private mapToken(token: any): Token {
    return new Token(token.access_token, token.token_type,
      token.refresh_token, token.expires_in, token.scope, token.jti, token.userId);
  }
}
