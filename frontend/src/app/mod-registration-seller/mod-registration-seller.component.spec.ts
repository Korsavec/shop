import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModRegistrationSellerComponent} from './mod-registration-seller.component';

describe('ModRegistrationSellerComponent', () => {
  let component: ModRegistrationSellerComponent;
  let fixture: ComponentFixture<ModRegistrationSellerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModRegistrationSellerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModRegistrationSellerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
