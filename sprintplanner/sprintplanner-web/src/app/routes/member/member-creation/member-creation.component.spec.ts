import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberCreationComponent } from './member-creation.component';

describe('MemberCreationComponent', () => {
  let component: MemberCreationComponent;
  let fixture: ComponentFixture<MemberCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
