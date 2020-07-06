import { Component, OnInit } from '@angular/core';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Config } from 'src/app/shared/services/config';
import { Subscription } from 'rxjs';
import { Branch } from 'src/app/models/branch.model';
import { GridOptions } from 'ag-grid-community';
import { BooleanRendererComponent } from 'src/app/shared/components/grid-components/boolean-renderer/boolean-renderer.component';
import { JiraStateRendererComponent } from 'src/app/shared/components/grid-components/jira-state-renderer.component';

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
  private repository: string;

  gridOptions: GridOptions;
  gridApi: any;
  gridColumnApi: any;

  branches: Branch[];
  repositories: string[] = [];
  rowData: any[] = [];

  constructor(public http: HttpRequestBuilder) {
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'Ticket', field: 'key', width: 120 },
        { headerName: 'Proof', field: 'testProofed', width: 90, cellRendererFramework: BooleanRendererComponent },
        { headerName: 'State', field: 'jiraState', width: 100, cellRendererFramework: JiraStateRendererComponent },
        { headerName: 'merged', field: 'creationDate', width: 150 },
        { headerName: 'Title', field: 'title', width: 600 },
        { headerName: 'Fix Version', field: 'fixVersion' },
        { headerName: 'Priority', field: 'priority' },
        { headerName: 'Type', field: 'type' },
        { headerName: 'Reporter', field: 'reporter' },
        { headerName: 'Assignee', field: 'assignee' },
        { headerName: 'Requestor', field: 'requestor' },
        { headerName: 'Parent', field: 'parent' }
      ]
    }
  }

  onGridReady(grid) {
    this.gridOptions.api.hideOverlay();
    this.gridApi = grid.api;
    this.gridColumnApi = grid.columnApi;
  }

  onChange(repository: string) {
    this.getBranches(repository);
  }

  ngOnInit() {
    this.getBranches(this.repository);
    this.getRepositories();
  }

  private getBranches(repository?: string) {
    let url = Config.endpoints.reconciliation.branches;
    if(repository) {
      url += "?repository=" + repository;
    }
    if (this.branchesSubscription) {
      this.branchesSubscription.unsubscribe();
    }
    this.branchesSubscription = this.http.get(url)
    .subscribe((branches: Branch[]) => {
      this.branches = branches;
    });
  }

  private getRepositories() {
    this.http.get(Config.endpoints.reconciliation.repositories)
    .subscribe((repositories: string[]) => {
      this.repositories = repositories;
    });
  }

  getReconciliatedIssues() {
    this.rowData = [];
    if (this.reconciliationSubscription) {
      this.reconciliationSubscription.unsubscribe();
    }
    const currentBranchParam = Config.params.currentBranch + this.currentBranch.commit.sha;
    const previousBranchParam = Config.params.previousBranch + this.previousBranch.commit.sha;
    this.reconciliationSubscription = this.http.get(Config.endpoints.reconciliation.reconciliate + currentBranchParam + previousBranchParam)
      .subscribe((issues: any[]) => {
        this.rowData = issues
      });

  }
}
