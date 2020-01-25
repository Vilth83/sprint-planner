import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberModificationComponent } from './member-modification.component';
import { AgGridModule } from 'ag-grid-angular';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { ConfirmationModalComponent } from 'src/app/shared/components/confirmation-modal/confirmation-modal.component';
import { MemberHttpRequest } from 'src/app/shared/services/http-helper/member-http-request.service';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule, BsModalService } from 'ngx-bootstrap/modal';

describe('MemberModificationComponent', () => {
  let component: MemberModificationComponent;
  let fixture: ComponentFixture<MemberModificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        MemberModificationComponent,
        InformationModalComponent,
        ConfirmationModalComponent
      ],
      imports: [
        AgGridModule,
        HttpClientModule,
        ModalModule.forRoot()
      ],
      providers: [MemberHttpRequest, BsModalService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberModificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
