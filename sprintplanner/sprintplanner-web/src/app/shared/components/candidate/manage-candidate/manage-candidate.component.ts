import { Component, OnInit, Input } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { IdDto } from 'src/app/models/IdDto.model';
import { CandidateCreator } from 'src/app/models/CandidateCreator.model';
import { Task } from 'src/app/models/task.model';

@Component({
  selector: 'app-manage-candidate',
  templateUrl: './manage-candidate.component.html',
  styleUrls: ['./manage-candidate.component.css']
})
export class ManageCandidateComponent implements OnInit {

  @Input('task')
  task: string;
  taskObject: Task;

  selectedCandidate: number;
  nonCandidates: Member[] = [];

  gridOptions: GridOptions;
  rowData: Candidate[];
  frameworkComponents = {};

  constructor(private http: HttpRequestBuilder) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'priority', field: 'priority', editable: true},
        { headerName: 'firstname', field: 'member.firstname' },
        { headerName: 'lastname', field: 'member.lastname' },
        {
          headerName: 'status',
          editable: true,
          field: 'status',
          cellEditor: 'agSelectCellEditor',
          cellEditorParams: {
            values: ['UNAVAILABLE', 'AVAILABLE', 'CURRENT', 'NEXT']
          }
        },
        {
          headerName: 'save',
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onEditClick.bind(this),
            btnClass: 'btn btn-primary fa fa-save  fa-lg'
          }
        },
        {
          headerName: 'delete',
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onDeleteClick.bind(this),
            btnClass: 'btn btn-primary fa fa-trash fa-lg'
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

  public getCandidates() {
    this.http.get("/candidates/" + this.task).subscribe((candidates: Candidate[]) => {
      this.rowData = candidates;
    })
  }

  public getNonCandidates() {
    this.http.get("/members/" + this.task + "/nonCandidates").subscribe((members: Member[]) => {
      this.nonCandidates = members;
    })
  }

  ngOnInit() {
    this.getCandidates();
    this.getNonCandidates();
    this.getTask();
  }

  onSaveClick() {
    const member: IdDto = new IdDto(this.selectedCandidate);
    const task: IdDto = new IdDto(this.taskObject.id);
    const candidate: CandidateCreator = { member: member, task: task };
    this.http.post("/candidates", candidate).subscribe(() => {
      this.getCandidates();
    });
  }

  onDeleteClick() {
    //
  }

  onEditClick() {
    //
  }

}
