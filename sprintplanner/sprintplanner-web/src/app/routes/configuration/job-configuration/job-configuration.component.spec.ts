import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobConfigurationComponent } from './job-configuration.component';

describe('JobConfigurationComponent', () => {
  let component: JobConfigurationComponent;
  let fixture: ComponentFixture<JobConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobConfigurationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
