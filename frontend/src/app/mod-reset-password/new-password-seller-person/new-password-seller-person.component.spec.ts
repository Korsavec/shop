import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NewPasswordSellerPersonComponent} from './new-password-seller-person.component';

describe('NewPasswordSellerPersonComponent', () => {
  let component: NewPasswordSellerPersonComponent;
  let fixture: ComponentFixture<NewPasswordSellerPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPasswordSellerPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewPasswordSellerPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
