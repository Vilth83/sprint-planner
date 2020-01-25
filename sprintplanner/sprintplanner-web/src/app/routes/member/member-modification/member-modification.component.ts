import { Component, OnInit, ViewChild } from '@angular/core';
import { Member } from 'src/app/models/member.model';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from 'src/app/shared/components/button-renderer.component';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { ConfirmationModalComponent } from 'src/app/shared/components/confirmation-modal/confirmation-modal.component';
import { MemberHttpRequest } from 'src/app/shared/services/http-helper/member-http-request.service';
import { Subscription, Observable } from 'rxjs';
import { ErrorHandler } from 'src/app/shared/services/error-handler.service';

@Component({
  selector: 'app-member-modification',
  templateUrl: './member-modification.component.html',
  styleUrls: ['./member-modification.component.css']
})
export class MemberModificationComponent implements OnInit {

  private deleteMemberSubscription: Subscription;
  private memberEditionSubscription: Subscription;
  private getMembersSubscription: Subscription;

  gridOptions: GridOptions;
  rowData: Member[];
  frameworkComponents = {};

  @ViewChild('info') private infoModal: InformationModalComponent;
  title: string;
  message: string;
  @ViewChild('confirmation') private confirmationModal: ConfirmationModalComponent;
  action: string;
  inputs: Member;


  constructor(private http: MemberHttpRequest) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { editable: true, sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: `firstname <span class="fa fa-edit></span>"`, field: 'firstname' },
        { headerName: 'lastname', field: 'lastname' },
        { headerName: 'email', field: 'email' },
        {
          headerName: 'shift',
          field: 'shift',
          cellEditor: "agSelectCellEditor",
          cellEditorParams: {
            values: ["PAR", "BGL"]
          }
        },
        {
          hide: false,
          headerName: 'edit',
          editable: false,
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onEditClick.bind(this),
            btnClass: 'btn btn-primary fa fa-save  fa-lg'
          }
        },
        {
          hide: false,
          headerName: 'delete',
          editable: false,
          cellRenderer: 'buttonRenderer',
          cellRendererParams: {
            onClick: this.onDeleteClick.bind(this),
            btnClass: 'btn btn-primary fa fa-trash fa-lg'
          }
        }
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
    const request = this.http.get();
    request.subscribe((members: Member[]) => {
      this.rowData = members;
    })
  }

  private edit(member: Member) {
    let request: Observable<any>;
    if (member.id) {
      console.log("put : " + member.id, ", ", member.firstname, ", ", member.lastname)
      request = this.http.put(member);
    } else {
      request = this.http.post(member)
    }
    this.memberEditionSubscription = request.subscribe(() => {
      this.openInfoModal('Update is successful !', member.firstname + " infos has been updated");
      this.ngOnInit();
    }, (error) => {
      const message = ErrorHandler.catch(error);
      console.log(error)
      this.openInfoModal("An error has occurred...", message);
    });
    this.ngOnInit();
  }

  private delete(member: Member) {
    const name = member.firstname;
    const request = this.http.delete(member);
    this.deleteMemberSubscription =
      request.subscribe(() => {
        this.openInfoModal('Deletion is successful !', name + " has been deleted.");
        this.ngOnInit();
      }, (error) => {
        const message = ErrorHandler.catch(error);
        this.openInfoModal("An error has occurred...", message);
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
    this.confirmationModal.setConfirmation(
      () => { this.confirm() },
      () => { this.decline() });
  }

  private onDeleteClick(params: any) {
    this.openConfirmationModal('delete', params.rowData);
  }

  private onEditClick(params: any) {
    this.openConfirmationModal('modify', params.rowData);
  }

  public createNewData() {
    const newData = new Member();
    this.rowData = this.rowData.concat([newData]);
  }

  public confirm() {
    switch (this.action) {
      case 'modify':
        this.edit(this.inputs);
        break;
      case 'delete':
        this.delete(this.inputs);
        break;
      default:
        this.decline();
    }
  }

  private decline() {
    this.ngOnInit();
  }

  public ngOnDestroy() {
    this.http.unsubscribe(this.getMembersSubscription);
    this.http.unsubscribe(this.deleteMemberSubscription);
    this.http.unsubscribe(this.memberEditionSubscription);
  }
}
