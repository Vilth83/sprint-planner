import { UserCredentials } from 'src/app/models/user-credentials.model';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Config } from './config';
import { HttpClient } from '@angular/common/http';
import { Token } from 'src/app/models/token.model';

@Injectable({ providedIn: 'root' })
export class LoginService {

  constructor(public http: HttpClient) { }

  public createToken(data: UserCredentials): Observable<any> {
    const header = Config.grantType.password + '&username=' + data.username + '&password=' + data.password + Config.clientId;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded);
  }

  public refreshToken(token : Token) {
    const header = Config.grantType.refresh + Config.clientId + Config.refreshToken + token.refreshToken;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded);
  }
}
