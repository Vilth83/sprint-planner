import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { Config } from '../config';
@Injectable({
  providedIn: 'root',
})
export class HttpRequestBuilder {

  private baseUrl: string = Config.uris.api

  constructor(private http: HttpClient) { }

  public post(endpoint:string, inputs:any): Observable<any> {
    return this.http.post(this.baseUrl + endpoint, inputs);
  }

  public get(endpoint: string): Observable<any> {
    return this.http.get(this.baseUrl + endpoint)
  }

  public put(endpoint: string, inputs: any): Observable<any> {
    return this.http.put(this.baseUrl + endpoint, inputs)
  }

  public delete(endpoint: string, inputs: any) : Observable<any> {
    return this.http.request('delete', this.baseUrl + endpoint, { body: inputs });
  }

  public patch(endpoint: string, object: any): Observable<any> {
    return this.http.patch(this.baseUrl + endpoint, object);
  }

  public unsubscribe(subscription: Subscription): void {
    if(subscription) {
      subscription.unsubscribe();
    }
  }
}
