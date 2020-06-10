import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueReconciliationComponent } from './issue-reconciliation.component';

describe('IssueReconciliationComponent', () => {
  let component: IssueReconciliationComponent;
  let fixture: ComponentFixture<IssueReconciliationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IssueReconciliationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IssueReconciliationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
