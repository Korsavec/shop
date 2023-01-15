import {TestBed} from '@angular/core/testing';

import {StoreSellerPersonService} from './store-seller-person.service';

describe('StoreSellerPersonService', () => {
  let service: StoreSellerPersonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StoreSellerPersonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
