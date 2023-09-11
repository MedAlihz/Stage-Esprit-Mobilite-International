import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { OfferUpdateService } from '../offer-update.service';
import { OffreService } from '../offre.service';
import { offre } from '../core/model/offre';
import { CandidatureService } from '../candidature.service';

@Component({
  selector: 'app-list-offer',
  templateUrl: './list-offer.component.html',
  styleUrls: ['./list-offer.component.css']
})
export class ListOfferComponent implements OnInit {
  offres: any[];
  constructor(
    private service : OffreService,
    private candidatureservice:CandidatureService,
    private router:Router
  ) {}
  public imageURL: string;
  file: File | null = null;
  message: any;
 
  ngOnInit(): void {
    this.getOffers();
    };
 processImages(): void {
    for (const offre of this.offres) {
      offre.image = 'data:image/jpeg;base64,' + offre.image;
    }
  }
  
  getOffers(){
     this.service.FindOffers().subscribe(data => {
      this.offres = data as any[];
      this.processImages();
    });
    /*this.service.FindOffers().subscribe((data) => {
      this.Offrelist = data as offre[];
    });*/
  }
 
  redirectToCandidatureForm(selectedOffer: offre) {
    // Redirect to the candidature form and pass the selected offer's information as a query parameter
    this.router.navigate(['/user/Postuler'], { queryParams: { idOffre: selectedOffer.idOffre } });
  }

}