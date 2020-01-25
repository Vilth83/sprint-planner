import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaserComponent } from './releaser.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ManageCandidateComponent } from 'src/app/shared/components/candidate/manage-candidate/manage-candidate.component';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { ConfirmationModalComponent } from 'src/app/shared/components/confirmation-modal/confirmation-modal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


describe('ReleaserComponent', () => {
  let component: ReleaserComponent;
  let fixture: ComponentFixture<ReleaserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ReleaserComponent, ManageCandidateComponent, InformationModalComponent, ConfirmationModalComponent],
      imports: [CollapseModule, AgGridModule, FormsModule, HttpClientTestingModule,
        ModalModule.forRoot(),BrowserAnimationsModule],
      providers: [BsModalRef]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReleaserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
