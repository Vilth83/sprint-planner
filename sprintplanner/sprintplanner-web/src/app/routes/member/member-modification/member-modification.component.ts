import { Component, OnInit } from '@angular/core';
import { HttpRequestBuilder } from 'src/app/shared/services/http-request-builder.service';
import { Member } from 'src/app/models/member/member.model';
import { GridOptions } from 'ag-grid-community';

@Component({
  selector: 'app-member-modification',
  templateUrl: './member-modification.component.html',
  styleUrls: ['./member-modification.component.css']
})
export class MemberModificationComponent implements OnInit {

  private endpoint = "/members";
  private gridOptions: GridOptions;
  private rowData: Member[];

  constructor(private httpBuilder: HttpRequestBuilder) {
    this.gridOptions = {
      defaultColDef: { resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'firstname', field: 'firstname' },
        { headerName: 'lastname', field: 'lastname' },
        { headerName: 'email', field: 'email' }
      ],
      onFirstDataRendered: this.sizeColumnsToFit
    };
  }
  ngOnInit() {
    this.getMembers();
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public getMembers() {
    const request = this.httpBuilder.get(this.endpoint);
    request.subscribe((members: Member[]) => {
      this.rowData = members;
    })
  }
}
