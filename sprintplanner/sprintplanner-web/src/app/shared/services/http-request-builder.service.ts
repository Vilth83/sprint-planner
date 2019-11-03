import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class HttpRequestBuilder {

  private baseUrl: string = "http://localhost:8081/api";

  constructor(private http: HttpClient) { }

  public post(url:string, inputs:any): Observable<any> {
    return this.http.post(this.baseUrl + url, inputs);
  }

  get(endpoint: string): Observable<any> {
    return this.http.get(this.baseUrl + endpoint)
  }

}
