import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class HttpErrorHandlerService implements ErrorHandler {

  constructor(private injector: Injector) { }
    handleError(error: any) {
      if (error instanceof HttpErrorResponse) {
          //Backend returns unsuccessful response codes such as 404, 500 etc.
          console.log(error)
          console.error('Backend returned status code: ', error.status);
          console.error('Response body:', error.message);
          let router = this.injector.get(Router);
          console.log('URL: ' + router.url);
      } else {
          //A client-side or network error occurred.
          console.log('An error occurred:', error);
      }
    }
}
