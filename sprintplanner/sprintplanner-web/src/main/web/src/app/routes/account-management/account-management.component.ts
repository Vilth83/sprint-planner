import { Component, OnInit } from '@angular/core';
import { GridOptions } from "ag-grid-community";
import { Member } from "../../models/member.model";
import { Config } from 'src/app/shared/services/config';
import { HttpClient } from "@angular/common/http";
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { ButtonRendererComponent } from 'src/app/shared/components/button-renderer.component';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css']
})
export class AccountManagementComponent implements OnInit {

  title = "Account Management";
  subtitle = "Activate / deactivate users accounts and change roles"

  gridOptions: GridOptions;
  rowData: Member[];
  frameworkComponents = {};
  constructor(private http: HttpClient, private authService: AuthenticationService) {



    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      rowClassRules: {
        'green_row': function(params) { return params.data.activated === true; },
        'red_row': function(params) { return params.data.activated === false; }
      },
      rowHeight: 50,
      defaultColDef: { editable: this.authService.isAdmin(), sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: `firstname`, field: 'firstname', editable: false },
        { headerName: 'lastname', field: 'lastname', editable: false },
        { headerName: 'username', field: 'username', editable: false },
        {
          hide: !this.authService.isAdmin(),
          headerName: 'activated',
          field: 'activated',
          editable: false,
          cellRenderer: 'buttonRenderer',
          width: 160,
          cellRendererParams: {
            onClick: this.onActivationClick.bind(this),
            btnClass: 'btn btn-primary fa fa-check-square fa-lg'
          }
        },
        {
          hide: !this.authService.isAdmin(),
          headerName: 'is admin',
          field: 'role.code',
          editable: false,
          width: 160,
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onRoleChange.bind(this),
            btnClass: 'btn btn-primary fa fa-check-square fa-lg'
          }
        }
      ]
    };
    onFirstDataRendered: this.sizeColumnsToFit

  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.http.get(Config.uris.api + '/users/all').subscribe((users: any) => {
      this.rowData = users;
    });
  }

  onActivationClick(params: any) {
    this.authService.activateAccount(params.rowData).subscribe(() => this.getUsers());
  }

  onRoleChange(params: any) {
    this.authService.toggleAdminRole(params.rowData).subscribe(() => this.getUsers());
  }
}
