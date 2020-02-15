import { Component, OnInit, Input } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { Task } from 'src/app/models/task.model';
import { Subscription } from 'rxjs';
import { ERROR_NO_CURRENT_CANDIDATE } from 'src/app/shared/constants';

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
	overlayNoRowsTemplate: string;

	title: string;
	message: string;

	candidateEditionSubscription: Subscription;
	deleteMemberSubscription: Subscription;


	constructor(private http: HttpRequestBuilder) {
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
		this.overlayNoRowsTemplate = ERROR_NO_CURRENT_CANDIDATE;
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
    }, () => {
			this.gridOptions.api.showNoRowsOverlay();
		})
  }

  ngOnInit() {
    this.getCurrentCandidate(this.task);
  }
}
