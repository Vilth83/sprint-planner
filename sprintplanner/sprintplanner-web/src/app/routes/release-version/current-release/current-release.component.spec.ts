import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentReleaseComponent } from './current-release.component';

describe('CurrentReleaseComponent', () => {
  let component: CurrentReleaseComponent;
  let fixture: ComponentFixture<CurrentReleaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentReleaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentReleaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
