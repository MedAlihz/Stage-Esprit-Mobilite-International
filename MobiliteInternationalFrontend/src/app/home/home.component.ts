import { Component,OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { OffreService } from '../offre.service';
import { OfferUpdateService } from '../offer-update.service';
import { offre } from '../core/model/offre';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  offres: any[];
  O : offre =new offre(0,"","","",new Date(),0,"","");
  selectedOffer: offre | null = null;
  updateForm: FormGroup;
  showUpdateForm: boolean = false; // New variable to control form visibility
  updatedOffre: any = {}; // Object to store updated user data

  constructor(
    private service : OffreService,
    private location: Location,
    private router: Router,
    private http: HttpClient,
    private snackBar: MatSnackBar,
    private sanitizer: DomSanitizer,
    private fb: FormBuilder, 
  ) {this.updateForm = this.fb.group({
    titre: ['', Validators.required],
    description: ['', Validators.required],
    lieu: ['', Validators.required],
    nombreplaces: ['', Validators.required],
    deadline: ['', Validators.required],
    link: ['', Validators.required]
  });}
  public imageURL: string;
  titre: string = '';
  description: string = '';
  lieu: string = '';
  deadline: string = '';
  nombreplaces: number = 0;
  link: string = '';
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
  handleFileInput(event: any) {
    this.file = event.target.files[0];
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

  deleteoffers(id: number) {
    this.service.Delete(id).subscribe(
      response => {
        console.log("Offer deleted successfully.");
        this.getOffers();
      },
      error => {
        console.log("Error while deleting Offer:", error);
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





 
refreshPage() {
  window.location.reload();
}


 // Method to toggle the update form visibility
 toggleUpdateForm(user: any) {
  this.showUpdateForm = !this.showUpdateForm;
  this.updatedOffre = { ...user }; // Copy user data for editing
}

// Method to update user information
updateOffreInfo(updatedOffre: any): void {
  const currentDate = new Date();
  const deadlineDate = new Date(updatedOffre.deadline);

  if (deadlineDate <= currentDate) {
    console.error('Invalid Date.');
    this.showSnackBar('Deadline Doit etre dans le future', 'Error');
  
    return;
    
  }

  const dateString = new Date(updatedOffre.deadline).toISOString().split('T')[0];

  this.service.UpdateOffre(updatedOffre.idOffre, this.file, updatedOffre.titre, updatedOffre.description, updatedOffre.lieu,dateString, updatedOffre.nombreplaces, updatedOffre.link).subscribe(
    (response) => {
      // Handle the successful response here
      console.log('Offre updated successfully', response);
      this.showUpdateForm = false; // Hide the form after updating
      this.getOffers(); // Refresh the user list
      window.location.reload(); // Reload the page
    },
    (error) => {
      // Handle any errors here
      console.error('Error updating Offre', error);
    }
  );
}

// Method to cancel the update operation and hide the form
cancelUpdate() {
  this.showUpdateForm = false;
}
redirectToTopCandidatures(selectedOffer: offre) {
  // Redirect to the candidature list and pass the selected offer's information as a query parameter
  this.router.navigate(['/admin/TopCandidature'], { queryParams: { idOffre: selectedOffer.idOffre } });
}

}
  