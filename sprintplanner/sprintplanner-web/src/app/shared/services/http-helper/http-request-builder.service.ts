import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class HttpRequestBuilder {

  private baseUrl: string = "http://localhost:8081/api";

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

}
