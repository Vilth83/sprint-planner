import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { IdDto } from 'src/app/models/IdDto.model';
import { CandidateCreator } from 'src/app/models/CandidateCreator.model';
import { Task } from 'src/app/models/task.model';
import { CandidateEditorDto } from 'src/app/models/candidate-edit-dto.model';
import { ConfirmationModalComponent } from '../../confirmation-modal/confirmation-modal.component';
import { Observable, Subscription } from 'rxjs';
import { CandidateHttpRequest } from 'src/app/shared/services/http-helper/candidate-http-request.service';
import { InformationModalComponent } from '../../information-modal/information-modal.component';
import { ErrorHandler } from 'src/app/shared/services/error-handler.service';

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

  title: string;
  message: string;

  @ViewChild('confirmation')
  private confirmationModal: ConfirmationModalComponent;
  @ViewChild('info')
  private infoModal: InformationModalComponent;

  candidateEditionSubscription: Subscription;
  deleteMemberSubscription: Subscription;



  constructor(private http: HttpRequestBuilder, private candidateService: CandidateHttpRequest) {
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
    this.candidateService.post(candidate).subscribe(() => {
      this.ngOnInit();
    });
  }

  onDeleteClick(params: any) {
    const candidate: Candidate = params.rowData;
    this.openConfirmationModal('delete', candidate);
  }

  onEditClick(params: any) {
    const candidate: Candidate = params.rowData;
    this.openConfirmationModal('modify', candidate);
  }

  public confirm(action: string, inputs: Candidate) {
    switch (action) {
      case 'modify':
        const edited: CandidateEditorDto =
          { id: inputs.id, priority: inputs.priority, status: inputs.status };
        this.edit(edited);
        break;
      case 'delete':
        this.delete(inputs);
        break;
      default:
        this.decline();
    }
  }

  public openConfirmationModal(action: string, inputs: any) {
    this.confirmationModal.openModal(this.confirmationModal.template);
    this.confirmationModal.setConfirmation(
      () => { this.confirm(action, inputs) },
      () => { this.decline() });
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }

  private decline() {
    this.ngOnInit();
  }

  private edit(candidate: CandidateEditorDto) {
    let request: Observable<any> = this.candidateService.put(candidate);
    this.candidateEditionSubscription = request.subscribe(() => {
      this.ngOnInit();
    }, (error) => {
      const message = ErrorHandler.catch(error);
      this.openInfoModal("An error has occurred...", message);
    });
    this.ngOnInit();
  }

  private delete(candidate: Candidate) {
    const request = this.candidateService.delete(candidate);
    this.deleteMemberSubscription =
      request.subscribe(() => {
        this.ngOnInit();
      }, (error) => {
        const message = ErrorHandler.catch(error);
        this.openInfoModal("An error has occurred...", message);
      });
    this.ngOnInit();
  }
}
