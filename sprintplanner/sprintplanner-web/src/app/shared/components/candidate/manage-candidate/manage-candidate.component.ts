import { Component, OnInit, Input } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';

@Component({
  selector: 'app-manage-candidate',
  templateUrl: './manage-candidate.component.html',
  styleUrls: ['./manage-candidate.component.css']
})
export class ManageCandidateComponent implements OnInit {

  @Input('task')
  task: string;

  nonCandidates: Member[] = [];

  gridOptions: GridOptions;
  rowData: Candidate[];
  frameworkComponents = {};

  constructor(private http: HttpRequestBuilder) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      defaultColDef: { editable: true, sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'priority', field: 'priority' },
        { headerName: 'firstname', field: 'member.firstname' },
        { headerName: 'lastname', field: 'member.lastname' },
        {
          headerName: 'status',
          field: 'status',
          cellEditor: 'agSelectCellEditor',
          cellEditorParams: {
            values: ['UNAVAILABLE', 'AVAILABLE', 'CURRENT', 'NEXT']
          }
        },
        {
          width: 80,
          headerName: 'save',
          editable: false,
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onEditClick.bind(this),
            btnClass: 'btn-cell fa fa-save  fa-lg'
          }
        },
        {
          width: 80,
          headerName: 'delete',
          editable: false,
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onDeleteClick.bind(this),
            btnClass: 'btn-cell fa fa-trash fa-lg'
          }
        }
      ],
      onFirstDataRendered: this.sizeColumnsToFit
    }
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
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
  }

  onDeleteClick() {
    //
  }

  onEditClick() {
    //
  }

}
