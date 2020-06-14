import { Component, OnInit, ViewChild } from '@angular/core';
import { Member } from 'src/app/models/member.model';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from 'src/app/shared/components/button-renderer.component';
import { InformationModalComponent } from 'src/app/shared/modals/index';
import { ConfirmationModalComponent } from 'src/app/shared/modals/index';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'src/app/shared/services/error-handler.service';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Config } from 'src/app/shared/services/config';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';

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


  constructor(private http: HttpRequestBuilder, private authService: AuthenticationService) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent
    }
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { editable: this.isAdmin(), sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: `firstname`, field: 'firstname' },
        { headerName: 'lastname', field: 'lastname' },
        { headerName: 'email', field: 'email' },
        {
          headerName: 'shift',
          field: 'shift',
          width: 120,
          cellEditor: "agSelectCellEditor",
          cellEditorParams: {
            values: ["PAR", "BGL"]
          }
        },
        {
          hide: !this.authService.isAdmin(),
          headerName: 'delete',
          editable: false,
          width: 160,
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
    const request = this.http.get(Config.endpoints.members);
    request.subscribe((members: Member[]) => {
      this.rowData = members;
    })
  }

  private delete(member: Member) {
    const request = this.http.delete(Config.endpoints.members + '/' + member.id, { id: member.id });
    this.deleteMemberSubscription =
      request.subscribe(() => {
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

  public createNewData() {
    const newData = new Member();
    this.rowData = this.rowData.concat([newData]);
  }

  public confirm() {
    this.delete(this.inputs);
  }

  private decline() {
    this.ngOnInit();
  }

  public ngOnDestroy() {
    this.http.unsubscribe(this.getMembersSubscription);
    this.http.unsubscribe(this.deleteMemberSubscription);
    this.http.unsubscribe(this.memberEditionSubscription);
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  editExisting(member: Member) {
    const request = this.http.put(Config.endpoints.members + '/' + member.id, member);

    this.memberEditionSubscription = request.subscribe(() => {
      // no action, rowData is updated automatically by agGrid.
    }, (error) => {
      const message = ErrorHandler.catch(error);
      this.openInfoModal("An error has occurred...", message);
    });
    this.ngOnInit();
  }

  createMember(member: Member) {
    if (this.isValid(member)) {
      const request = this.http.post(Config.endpoints.members, member)

      this.memberEditionSubscription = request.subscribe(
        () => this.getMembers(),
        (error) => {
          const message = ErrorHandler.catch(error);
          this.openInfoModal("An error has occurred...", message);
        });
    }
  }

  private isValid(member: Member) {
    return member.hasOwnProperty("shift")
      && member.hasOwnProperty("firstname")
      && member.hasOwnProperty("lastname")
      && member.hasOwnProperty("email");
  }

  onCellValueChanged(event: any) {
    if (event.data.hasOwnProperty('id')) {
      this.editExisting(event.data);
    } else {
      this.createMember(event.data);
    }
  }
}
