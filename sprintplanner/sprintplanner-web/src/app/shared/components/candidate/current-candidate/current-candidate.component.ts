import { Component, OnInit, Input } from '@angular/core';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Task } from 'src/app/models/task.model';
import { Subscription, Observable } from 'rxjs';
import { Shift } from 'src/app/models/shift.model';
import { Config } from 'src/app/shared/services/config';
import { CandidateEditorDto } from 'src/app/models/candidate-edit-dto.model';
import { Status } from 'src/app/models/status.model';
import { CandidateHttpRequest } from 'src/app/shared/services/http-helper/candidate-http-request.service';

@Component({
  selector: 'app-current-candidate',
  templateUrl: './current-candidate.component.html',
  styleUrls: ['./current-candidate.component.css']
})
export class CurrentCandidateComponent implements OnInit {

  @Input('task')
  task: string;
  @Input()
  shift: string;
  taskObject: Task;
  taskTitle: string = "";

  currentCandidate: Candidate;
  currentCandidateName: string = "";

  title: string;
  message: string;

  candidateEditionSubscription: Subscription;
  deleteMemberSubscription: Subscription;

  private endpoint = "/candidates";


  constructor(private http: HttpRequestBuilder, private candidateService: CandidateHttpRequest) {
  }


  public getTask() {
    this.http.get(Config.endpoints.tasks + '/' + this.task + "/name").subscribe(task => {
      this.taskObject = task;
      this.taskTitle = task;
    });
  }

  public getCurrentCandidate() {
    let url = "/candidates/" + this.task + "/current";
    this.taskTitle = this.getTaskTitle();
    if (this.shift) {
      url += "/" + this.shift;
    }
    this.http.get(url).subscribe((candidate: Candidate) => {
      this.currentCandidate = candidate;
      this.currentCandidateName = candidate.member.firstname + " " + candidate.member.lastname;
    })
  }

  public getAvailableCandidate(): void {
    let url = "/candidates/" + this.task + "/available";
    this.taskTitle = this.getTaskTitle();
    if (this.shift) {
      url += "/" + this.shift;
    }
    this.http.get(url).subscribe((candidate: Candidate) => {
      console.log(candidate)
      const updateCandidate ={ id: candidate.id, priority: candidate.priority, status: Status.CURRENT };
      this.candidateService.updateToCurrent(updateCandidate, this.task, this.shift)
      .subscribe(
        ()=> this.ngOnInit()
      );
    })
  }

  private getTaskTitle(): string {
    if (this.shift == Shift.PAR) {
      return this.task + ' (Paris)';
    } else if (this.shift == Shift.BGL) {
      return this.task + ' (Bangalore)';
    } else {
      return this.task;
    }
  }


  ngOnInit() {
    this.getCurrentCandidate();
  }
}
