import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NewPasswordUserComponent} from './new-password-user.component';

describe('NewPasswordUserComponent', () => {
  let component: NewPasswordUserComponent;
  let fixture: ComponentFixture<NewPasswordUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPasswordUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewPasswordUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
