import { Injectable } from '@angular/core';
import { HttpRequestBuilder } from './http-request-builder.service';
import { Observable, Subscription } from 'rxjs';
import { CandidateCreateDto } from 'src/app/models/candidate-create-dto.model';
import { CandidateUpdateDto } from 'src/app/models/candidate-update-dto.model';
import { Candidate } from 'src/app/models/candidate.model';
import { Config } from '../config';

@Injectable({
  providedIn: 'root',
})
export class CandidateHttpRequest {


  public unsubscribe(subscription: Subscription): void {
    if (subscription) {
      subscription.unsubscribe();
    }
  }

  constructor(private http: HttpRequestBuilder) {

  }

  public post(inputs: CandidateCreateDto): Observable<any> {
    return this.http.post(Config.endpoints.candidates, inputs);
  }

  public delete(inputs: Candidate): Observable<any> {
    let url = Config.endpoints.candidates + "/" + inputs.id;
    const deletedCandidate = { id: inputs.id };
    return this.http.delete(url, deletedCandidate);
  }

  public update(inputs: CandidateUpdateDto): Observable<any> {
    let url = Config.endpoints.candidates + "/" + inputs.id;
    return this.http.put(url, inputs);
  }

  public getCandidates(task: string, shift?: string) {
    let url = Config.endpoints.candidates + "/" + task;
    url = this.enrichWithShift(url, shift, Config.appenders.param)
    return this.http.get(url);
  }

  public updateToCurrent(
    inputs: CandidateUpdateDto,
    taskName: string,
    shift?: string
  ): Observable<void> {
    let url : string =
      Config.endpoints.candidates + "/" + inputs.id +
      Config.endpoints.current + Config.params.taskName + taskName;
    url = this.enrichWithShift(url, shift, Config.appenders.and);
    return this.http.put(url, inputs);
  }

  enrichWithShift(url: string, shift: string, appender: string) {
    if (shift) {
      return url += appender + Config.params.shift + shift;
    }
    return url;
  }

  public getCurrentCandidate(task: string, shift?: string): Observable<Candidate> {
    let url = Config.endpoints.candidates + Config.appenders.slash + task + Config.endpoints.current;
    url = this.enrichWithShift(url, shift, Config.appenders.param)
    return this.http.get(url);
  }

  public getAvailableCandidate(task: string, shift?: string): Observable<Candidate> {
    let url = Config.endpoints.candidates + Config.appenders.slash + task + Config.endpoints.available;
    url = this.enrichWithShift(url, shift, Config.appenders.param)
    return this.http.get(url);
  }
}
