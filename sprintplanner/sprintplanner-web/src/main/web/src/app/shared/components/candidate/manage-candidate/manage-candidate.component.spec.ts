import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageCandidateComponent } from './manage-candidate.component';
import { AgGridModule } from 'ag-grid-angular';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ConfirmationModalComponent } from '../../confirmation-modal/confirmation-modal.component';
import { InformationModalComponent } from '../../information-modal/information-modal.component';

describe('ManageCandidateComponent', () => {
  let component: ManageCandidateComponent;
  let fixture: ComponentFixture<ManageCandidateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ManageCandidateComponent, ConfirmationModalComponent, InformationModalComponent],
      imports: [AgGridModule,
        HttpClientTestingModule,
        ModalModule.forRoot(),
        FormsModule,
        CollapseModule],
      providers: [BsModalRef]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
