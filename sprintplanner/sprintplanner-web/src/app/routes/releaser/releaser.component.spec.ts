import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaserComponent } from './releaser.component';

describe('ReleaserComponent', () => {
  let component: ReleaserComponent;
  let fixture: ComponentFixture<ReleaserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReleaserComponent ]
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
