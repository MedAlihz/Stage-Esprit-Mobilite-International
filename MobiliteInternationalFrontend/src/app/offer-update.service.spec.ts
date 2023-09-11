import { TestBed } from '@angular/core/testing';

import { OfferUpdateService } from './offer-update.service';

describe('OfferUpdateService', () => {
  let service: OfferUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfferUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
