import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentCandidateComponent } from './current-candidate.component';

describe('CurrentCandidateComponent', () => {
  let component: CurrentCandidateComponent;
  let fixture: ComponentFixture<CurrentCandidateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentCandidateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
