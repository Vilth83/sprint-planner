import { Component, OnInit } from '@angular/core';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Config } from 'src/app/shared/services/config';
import { Subscription } from 'rxjs';
import { Branch } from 'src/app/models/branch.model';
import { GridOptions } from 'ag-grid-community';
import { BooleanRendererComponent } from 'src/app/shared/components/grid-components/boolean-renderer/boolean-renderer.component';

@Component({
  selector: 'app-issue-reconciliation',
  templateUrl: './issue-reconciliation.component.html',
  styleUrls: ['./issue-reconciliation.component.css']
})
export class IssueReconciliationComponent implements OnInit {

  private branchesSubscription: Subscription;
  private reconciliationSubscription: Subscription;


  private currentBranch: Branch;
  private previousBranch: Branch;

  gridOptions: GridOptions;
  gridApi: any;
  gridColumnApi: any;

  branches: Branch[];
  rowData: Branch[];

  constructor(public http: HttpRequestBuilder) {
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'Ticket', field: 'key', width: 120 },
        { headerName: 'Proof', field: 'testProofed', width: 90, cellRendererFramework: BooleanRendererComponent },
        { headerName: 'State', field: 'state', width: 100 },
        { headerName: 'merged', field: 'creationDate', width: 150 },
        { headerName: 'Title', field: 'title', width: 600 },
        { headerName: 'Fix Version', field: 'fixVersion' },
        { headerName: 'Priority', field: 'priority' },
        { headerName: 'Requestor', field: 'requestor' },
        { headerName: 'Type', field: 'type' },
        { headerName: 'Reporter', field: 'reporter' },
        { headerName: 'Assignee', field: 'assignee' },
        { headerName: 'Parent', field: 'parent' },
        { headerName: 'Reporter', field: 'reporter' }
      ]
    }
  }

  onGridReady(grid) {
    this.gridOptions.api.hideOverlay();
    this.gridApi = grid.api;
    this.gridColumnApi = grid.columnApi;
  }

  ngOnInit() {
    this.getBranches();
  }

  private getBranches() {
    if (this.branchesSubscription) {
      this.branchesSubscription.unsubscribe();
    }
    this.branchesSubscription = this.http.get(Config.endpoints.reconciliation.branches).subscribe((branches: Branch[]) => {
      this.branches = branches;
    });
  }

  getReconciliatedIssues() {
    if (this.reconciliationSubscription) {
      this.reconciliationSubscription.unsubscribe();
    }
    const currentBranchParam = Config.params.currentBranch + this.currentBranch.commit.sha;
    const previousBranchParam = Config.params.previousBranch + this.previousBranch.commit.sha;
    this.reconciliationSubscription = this.http.get(Config.endpoints.reconciliation.reconciliate + currentBranchParam + previousBranchParam)
      .subscribe((issues: any[]) => this.rowData = issues, error => console.error(error));
  }
}
