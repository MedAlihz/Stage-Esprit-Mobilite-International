import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { offre } from '../core/model/offre';
import { OffreService } from '../offre.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CandidatureService } from '../candidature.service';
import { UserService } from '../core/service/user.service';
import { SigninComponent } from '../signin/signin.component';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-candidature-form',
  templateUrl: './candidature-form.component.html',
  styleUrls: ['./candidature-form.component.css']
})
export class CandidatureFormComponent implements OnInit {
  selectedOffer: offre;
  candidatureData: any = {};
  offres: any[];
  email: string;
  idUser: any; // Set the user ID.

Email:any;
  constructor(
    private route: ActivatedRoute,
    private offreService: OffreService,
    private service:CandidatureService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params: Params) => {
      const idOffre = +params['idOffre'];
      console.log(idOffre) // Convert offerId to a number using the '+' operator
      this.getOfferById(idOffre);
     
    });
 
}



  getOfferById(offerId: number) {
    this.offreService.getOfferById(offerId).subscribe(
      (offer: offre) => {
        this.selectedOffer = offer;
      },
      (error) => {
        console.error('Error fetching offer by ID:', error);
      }
    );
  }
  private showSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 10000, // Duration in milliseconds
      verticalPosition: 'top', // Position at the top of the screen
      horizontalPosition: 'center' // Centered horizontally
    });
  }
  submitCandidatureForm() {
    if (!this.selectedOffer) {
      console.error('No offer selected. Cannot submit candidature without an offer.');
      return;
    }
    if (this.candidatureData.moy3 < 0 || this.candidatureData.moy3 > 20 ||
      this.candidatureData.moy2 < 0 || this.candidatureData.moy2 > 20 ||
      this.candidatureData.moy1 < 0 || this.candidatureData.moy1 > 20) {
    console.error('Valeurs moyennes non valides. Veuillez saisir des valeurs entre 0 et 20.');
    this.showSnackBar('Valeurs moyennes non valides. Veuillez saisir des valeurs entre 0 et 20.', 'Error');

    return;
  }
  if (this.candidatureData.classe[0]!="4") {
  console.error('Invalid Classe.');
  this.showSnackBar('Classe doit etre 4éme', 'Error');

  return;
}


// Check email validity asynchronously
this.service.isValidEmail(this.candidatureData.email).subscribe(
  (isValid) => {
    if (!isValid) {
      console.error('Adresse e-mail inexistante');
      this.showSnackBar('Adresse e-mail inexistante. Veuillez saisir une adresse e-mail valide', 'Error');
      return;
    }

    const candidature = {
      ...this.selectedOffer,
      ...this.candidatureData,
      
    };
    this.service.SubmitApplication(this.selectedOffer.idOffre,candidature).subscribe(
    (response) => {
      // Handle the successful response from the server if needed
      console.log('Candidature added successfully:', response);
      console.log(this.selectedOffer);
      // Optionally, you can redirect the user to a success page or perform other actions.
    },
    (error) => {
      // Handle the error response from the server if needed
      console.error('Error adding candidature:', error);
    }
  );
  this.router.navigate(['user/ListCandidature']);

}

);

  }
}
  