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
    const token = this.tokenStore.getToken();
    if (this.tokenStore.checkToken()) {
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token.accessToken
        }
      });
    }
    return next.handle(request);
  }
}
