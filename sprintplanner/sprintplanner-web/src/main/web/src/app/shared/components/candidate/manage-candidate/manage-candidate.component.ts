import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ButtonRendererComponent } from '../../button-renderer.component';
import { Candidate } from 'src/app/models/candidate.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { Member } from 'src/app/models/member.model';
import { IdDto } from 'src/app/models/IdDto.model';
import { CandidateCreateDto } from 'src/app/models/candidate-create-dto.model';
import { Task } from 'src/app/models/task.model';
import { CandidateUpdateDto } from 'src/app/models/candidate-update-dto.model';
import { ConfirmationModalComponent } from 'src/app/shared/modals/index';
import { Observable, Subscription } from 'rxjs';
import { CandidateHttpRequest } from 'src/app/shared/services/http-helper/candidate-http-request.service';
import { InformationModalComponent } from 'src/app/shared/modals/index';
import { ErrorHandler } from 'src/app/shared/services/error-handler.service';
import { ERROR_NO_CANDIDATE } from 'src/app/shared/constants';
import { Status } from 'src/app/models/status.model';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { Shift } from 'src/app/models/shift.model';

@Component({
  selector: 'app-manage-candidate',
  templateUrl: './manage-candidate.component.html',
  styleUrls: ['./manage-candidate.component.css']
})
export class ManageCandidateComponent implements OnInit {

  @Input('task')
  task: string;
  taskTitle: string = "";
  @Input('shift')
  shift: string;
  taskId: number;

  currentCandidateName: string = "";
  currentCandidate: Candidate;
  selectedCandidate: number;
  nonCandidates: Member[] = [];

  gridOptions: GridOptions;
  rowData: Candidate[];
  frameworkComponents = {};
  overlayNoRowsTemplate: string;

  private selectedMember: IdDto;

  title: string;
  message: string;

  @ViewChild('confirmation')
  private confirmationModal: ConfirmationModalComponent;
  @ViewChild('info')
  private infoModal: InformationModalComponent;

  candidateEditionSubscription: Subscription;
  deleteMemberSubscription: Subscription;

  constructor(
    private http: HttpRequestBuilder,
    private candidateService: CandidateHttpRequest,
    private authService: AuthenticationService
  ) {
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
          hide: !this.authService.isAdmin(),
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
    this.overlayNoRowsTemplate = ERROR_NO_CANDIDATE;
  }

  onSelectionChange(memberId: number) {
    this.selectedMember = new IdDto(memberId);
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public getTask() {
    this.http.get("/tasks/" + this.task + "/name").subscribe((task: Task) => {
      this.taskId = task.id;
    });
  }

  private getTaskTitle(): string {
    if (this.shift) {
      return this.task + ' support';
    } else {
      return this.task;
    }
  }

  public getCandidates() {
    this.candidateService.getCandidates(this.task, this.shift)
      .subscribe((candidates: Candidate[]) => {
        this.rowData = candidates;
        this.currentCandidate = candidates.find(candidate => candidate.status === Status.CURRENT);
        this.currentCandidateName =
          this.currentCandidate.member.firstname + " " + this.currentCandidate.member.lastname;
      }
      );
  }

  public getNonCandidates() {
    let url = "/members/" + this.task + "/nonCandidates";
    if (this.shift) {
      url += "?shift=" + this.shift;
    }
    this.http.get(url).subscribe((members: Member[]) => {
      this.nonCandidates = members;
    })
  }

  public setNextCurrentCandidate(): void {
    this.candidateService.getAvailableCandidate(this.task, this.shift)
      .subscribe((candidate: Candidate) => {
        const updateCandidate =
          new CandidateUpdateDto(candidate.id, candidate.priority, Status.CURRENT);
        this.candidateService.updateToCurrent(updateCandidate, this.task, this.shift)
          .subscribe(
            () => this.ngOnInit()
          );
      })
  }

  ngOnInit() {
    this.taskTitle = this.getTaskTitle();
    this.getCandidates();
    this.getNonCandidates();
    this.getTask();
  }

  onSaveClick() {
    const task: IdDto = new IdDto(this.taskId);
    const candidate: CandidateCreateDto = new CandidateCreateDto(this.selectedMember, task);
    this.candidateService.post(candidate).subscribe(() => {
      this.nonCandidates = [];
      this.ngOnInit();
    });
  }

  onDeleteClick(params: any) {
    const candidate: Candidate = params.rowData;
    this.openConfirmationModal(candidate);
  }

  public confirm(inputs: Candidate) {
    this.delete(inputs);

  }

  public openConfirmationModal(inputs: any) {
    this.confirmationModal.openModal(this.confirmationModal.template);
    this.confirmationModal.setConfirmation(
      () => { this.confirm(inputs) },
      () => { this.decline() });
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }

  onCellValueChanged(value: any) {
    const candidate: CandidateUpdateDto =
      new CandidateUpdateDto(value.data.id, value.data.priority, value.data.status);
    this.edit(candidate);
  }

  private decline() {
    this.ngOnInit();
  }

  private edit(candidate: CandidateUpdateDto) {
    let request: Observable<any>
    if (candidate.status == Status.CURRENT) {
      request = this.candidateService.updateToCurrent(candidate, this.task, this.shift);
    } else {
      request = this.candidateService.update(candidate);
    }
    this.candidateEditionSubscription = request.subscribe(() => {
      this.ngOnInit();
    }
    );
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
