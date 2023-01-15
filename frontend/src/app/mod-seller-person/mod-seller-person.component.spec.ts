import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModSellerPersonComponent} from './mod-seller-person.component';

describe('ModSellerPersonComponent', () => {
  let component: ModSellerPersonComponent;
  let fixture: ComponentFixture<ModSellerPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModSellerPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModSellerPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
