import { Injectable } from '@angular/core';
import { Config } from './../config';
import { HttpClient } from '@angular/common/http';
import { Token } from 'src/app/models/token.model';

@Injectable({ providedIn: 'root' })
export class LoginService {

  constructor(public http: HttpClient) { }

  public refreshToken(token : Token) {
    const header = Config.grantType.refresh + Config.clientId + Config.refreshToken + token.refreshToken;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded);
  }
}
