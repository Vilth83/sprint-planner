import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TokenStorageService } from './token-storage.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public tokenStore: TokenStorageService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.tokenStore.checkToken()) {
      const token = this.tokenStore.getToken();
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token.accessToken
        }
      });
    }
    return next.handle(request);
  }
}
