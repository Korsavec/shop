import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ResetPasswordSellerPersonComponent} from './reset-password-seller-person.component';

describe('ResetPasswordSellerPersonComponent', () => {
  let component: ResetPasswordSellerPersonComponent;
  let fixture: ComponentFixture<ResetPasswordSellerPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetPasswordSellerPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetPasswordSellerPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
