import { Injectable } from '@angular/core';
import { offre } from './core/model/offre';

@Injectable({
  providedIn: 'root'
})
export class OfferUpdateService {
 private offerToUpdate: offre | null = null;

  setOfferToUpdate(offer: offre) {
    this.offerToUpdate = offer;
  }

  getOfferToUpdate(): offre | null {
    return this.offerToUpdate;
  }
}
