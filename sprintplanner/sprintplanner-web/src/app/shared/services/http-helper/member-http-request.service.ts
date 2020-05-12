import { Injectable } from '@angular/core';
import { HttpRequestBuilder } from './http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { Observable } from 'rxjs';
import { MemberDeleteDto } from 'src/app/models/memberDeleteDto.model';

@Injectable({
  providedIn: 'root',
})
export class MemberHttpRequest {

  private endpoint = "/members";

  constructor(private http: HttpRequestBuilder) {

  }

  public get(): Observable<Member[]> {
    return this.http.get(this.endpoint);
  }

  public post(inputs: Member): Observable<any> {
    return this.http.post(this.endpoint, inputs);
  }

  public delete(inputs : Member): Observable<any> {
    let url = this.endpoint + "/" + inputs.id;
    const deletedMember : MemberDeleteDto = {id: inputs.id};
    return this.http.delete(url, deletedMember);
  }

  public put(inputs: Member): Observable<any> {
    let url = this.endpoint + "/" + inputs.id;
    return this.http.put(url, inputs);
  }
}
