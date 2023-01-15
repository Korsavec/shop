import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginSellerPersonComponent} from './login-seller-person.component';

describe('LoginSellerPersonComponent', () => {
  let component: LoginSellerPersonComponent;
  let fixture: ComponentFixture<LoginSellerPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginSellerPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginSellerPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
