import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpRequestBuilder } from 'src/app/shared/services/http-request-builder.service';
import { Member } from 'src/app/models/member/member.model';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from 'src/app/shared/components/button-renderer.component';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { ConfirmationModalComponent } from 'src/app/shared/components/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'app-member-modification',
  templateUrl: './member-modification.component.html',
  styleUrls: ['./member-modification.component.css']
})
export class MemberModificationComponent implements OnInit {

  private endpoint = "/members";
  gridOptions: GridOptions;
  rowData: Member[];
  frameworkComponents = {};

  @ViewChild('info') private infoModal: InformationModalComponent;
  title: string;
  message: string;
  @ViewChild('confirmation') private confirmationModal: ConfirmationModalComponent;
  action: string;
  inputs: any;


  constructor(private httpBuilder: HttpRequestBuilder) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      defaultColDef: { editable: true, sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'firstname', field: 'firstname' },
        { headerName: 'lastname', field: 'lastname' },
        { headerName: 'email', field: 'email' },
        {
          width: 80,
          headerName: 'edit',
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
        },

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

  private onEditClick(params: any) {
    this.openConfirmationModal('modify', params.rowData);
  }

  private edit() {
    let url = this.endpoint + "/" + this.inputs.id;
    const request = this.httpBuilder.put(url, this.inputs);

    request.subscribe(() => {
      this.openInfoModal('Update is successful !', this.inputs.firstname + " infos has been updated");
      this.ngOnInit();
    }, () => {
      this.openInfoModal("An error has occurred...", "")
    });
    this.ngOnInit();
  }


  private onDeleteClick(params: any) {
    this.openConfirmationModal('delete', params.rowData);
  }

  private delete() {
    let url = this.endpoint + "/" + this.inputs.id;
    const request = this.httpBuilder.delete(url);

    request.subscribe(() => {
      this.openInfoModal('Deletion is successful !', this.inputs.firstname + " has been deleted.");
      this.ngOnInit();
    }, () => {
      this.openInfoModal("An error has occurred...", this.inputs.firstname + " has not been deleted.")
    });
    this.ngOnInit();
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }

  public openConfirmationModal(action: string, inputs: any) {
    this.action = action;
    this.inputs = inputs;
    this.confirmationModal.openModal(this.confirmationModal.template);
    this.confirmationModal.setConfirmation(() => { this.confirm() }, () => { this.decline() });
  }

  public confirm() {
    switch (this.action) {
      case 'modify':
        this.edit();
        break;
      case 'delete':
        this.delete();
        break;
      default:
        this.decline();
    }
  }

  private decline() {
    this.ngOnInit();
  }
}
