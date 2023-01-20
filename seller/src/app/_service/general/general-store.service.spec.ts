import {TestBed} from '@angular/core/testing';

import {GeneralStoreService} from './general-store.service';

describe('GeneralStoreService', () => {
  let service: GeneralStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneralStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
