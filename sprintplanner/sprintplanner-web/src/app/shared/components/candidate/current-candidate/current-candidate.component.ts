import { Component, OnInit, Input } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Task } from 'src/app/models/task.model';
import { Subscription } from 'rxjs';
import { ERROR_NO_CURRENT_CANDIDATE } from 'src/app/shared/constants';
import { Shift } from 'src/app/models/shift.model';

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

  currentCandidate: String = "";

  title: string;
  message: string;

  candidateEditionSubscription: Subscription;
  deleteMemberSubscription: Subscription;


  constructor(private http: HttpRequestBuilder) {
  }


  public getTask() {
    this.http.get("/tasks/" + this.task + "/name").subscribe(task => {
      this.taskObject = task;
      this.taskTitle = task;
    }
    );
  }

  public getCurrentCandidate() {
    let url = "/candidates/" + this.task + "/current";
    this.taskTitle = this.getTaskTitle();
    if (this.shift) {
      url += "/" + this.shift;
    }
    this.http.get(url).subscribe((candidate: Candidate) => {
      this.currentCandidate = candidate.member.firstname + " " + candidate.member.lastname;
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
