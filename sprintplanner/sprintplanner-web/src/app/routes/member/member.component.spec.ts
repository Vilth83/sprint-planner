import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberComponent } from './member.component';
import { AgGridModule } from 'ag-grid-angular';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { ConfirmationModalComponent } from 'src/app/shared/components/confirmation-modal/confirmation-modal.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule, BsModalService } from 'ngx-bootstrap/modal';
import { MockComponent } from 'ng2-mock-component';


describe('MemberComponent', () => {
  let component: MemberComponent;
  let fixture: ComponentFixture<MemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        MemberComponent,
        InformationModalComponent,
        ConfirmationModalComponent,
        MockComponent({ selector: 'app-member-modification' })],
      imports: [AgGridModule, HttpClientModule, ModalModule.forRoot()],
      providers: [BsModalService]
    })
      .compileComponents();
  }));

  beforeEach(async () => {
    fixture = TestBed.createComponent(MemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.ngOnInit();

    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
