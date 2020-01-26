import { Injectable } from '@angular/core';
import { HttpRequestBuilder } from './http-request-builder.service';
import { Observable, Subscription } from 'rxjs';
import { CandidateCreator } from 'src/app/models/CandidateCreator.model';
import { CandidateEditorDto } from 'src/app/models/candidate-edit-dto.model';
import { Candidate } from 'src/app/models/candidate.model';

@Injectable({
  providedIn: 'root',
})
export class CandidateHttpRequest {


  public unsubscribe(subscription: Subscription): void {
    if (subscription) {
      subscription.unsubscribe();
    }
  }

  private endpoint = "/candidates";

  constructor(private http: HttpRequestBuilder) {

  }

  public get(): Observable<Candidate[]> {
    return this.http.get(this.endpoint);
  }

  public post(inputs: CandidateCreator): Observable<any> {
    return this.http.post(this.endpoint, inputs);
  }

  public delete(inputs: Candidate): Observable<any> {
    let url = this.endpoint + "/" + inputs.id;
    const deletedCandidate = { id: inputs.id };
    return this.http.delete(url, deletedCandidate);
  }

  public put(inputs: CandidateEditorDto): Observable<any> {
    let url = this.endpoint + "/" + inputs.id;
    return this.http.put(url, inputs);
  }
}
