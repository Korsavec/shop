import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModRegistrationUserComponent} from './mod-registration-user.component';

describe('ModRegistrationUserComponent', () => {
  let component: ModRegistrationUserComponent;
  let fixture: ComponentFixture<ModRegistrationUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModRegistrationUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModRegistrationUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
