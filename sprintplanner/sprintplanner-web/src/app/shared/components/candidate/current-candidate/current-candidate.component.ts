import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { IdDto } from 'src/app/models/IdDto.model';
import { CandidateCreator } from 'src/app/models/CandidateCreator.model';
import { Task } from 'src/app/models/task.model';
import { CandidateEditorDto } from 'src/app/models/candidate-edit-dto.model';
import { ConfirmationModalComponent } from '../../confirmation-modal/confirmation-modal.component';
import { Observable, Subscription } from 'rxjs';
import { CandidateHttpRequest } from 'src/app/shared/services/http-helper/candidate-http-request.service';
import { InformationModalComponent } from '../../information-modal/information-modal.component';
import { ErrorHandler } from 'src/app/shared/services/error-handler.service';

@Component({
	selector: 'app-current-candidate',
	templateUrl: './current-candidate.component.html',
	styleUrls: ['./current-candidate.component.css']
})
export class CurrentCandidateComponent implements OnInit {

	@Input('task')
	task: string;
	taskObject: Task;

	selectedCandidate: number;
	nonCandidates: Member[] = [];

	gridOptions: GridOptions;
	rowData: Candidate[];
	frameworkComponents = {};

	title: string;
	message: string;

	candidateEditionSubscription: Subscription;
	deleteMemberSubscription: Subscription;

	constructor(private http: HttpRequestBuilder, private candidateService: CandidateHttpRequest) {
		 this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'priority', field: 'priority', editable: true },
        { headerName: 'firstname', field: 'member.firstname' },
        { headerName: 'lastname', field: 'member.lastname' },
        {
          headerName: 'status',
          editable: true,
          field: 'status',
          cellEditor: 'agSelectCellEditor',
          cellEditorParams: {
            values: ['UNAVAILABLE', 'AVAILABLE', 'CURRENT']
          }
        }
      ],
      onFirstDataRendered: this.sizeColumnsToFit
    }
	 }
  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public getTask() {
    this.http.get("/tasks/" + this.task + "/name").subscribe(task =>
      this.taskObject = task);
  }

  public getCurrentCandidate(task: string) {
    this.http.get( "/candidates/" + task + "/current").subscribe((candidate: Candidate) => {
      this.rowData = [candidate];
    }, (error) => {
		this.rowData = [];
	})
  }

  ngOnInit() {
    this.getCurrentCandidate(this.task);
  }
}
