import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ErrorHandler {
  static catch(error: any) {
    const errors = error.error.errors;
    let message = "";
    errors.forEach((err: any) => message = message + err.field + " " + err.defaultMessage + ", " );
    message = message.slice(0, -2);
    return message;
  }
}
