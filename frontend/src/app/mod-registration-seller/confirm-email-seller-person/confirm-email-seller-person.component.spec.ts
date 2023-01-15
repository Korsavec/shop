import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConfirmEmailSellerPersonComponent} from './confirm-email-seller-person.component';

describe('ConfirmEmailSellerPersonComponent', () => {
  let component: ConfirmEmailSellerPersonComponent;
  let fixture: ComponentFixture<ConfirmEmailSellerPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmEmailSellerPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmEmailSellerPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
