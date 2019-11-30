import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberModificationComponent } from './member-modification.component';

describe('MemberModificationComponent', () => {
  let component: MemberModificationComponent;
  let fixture: ComponentFixture<MemberModificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberModificationComponent ]
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
