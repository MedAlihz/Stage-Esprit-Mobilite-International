import { Component, OnInit } from '@angular/core';
import { OffreService } from '../offre.service';
import { ActivatedRoute } from '@angular/router';
import { offre } from '../core/model/offre';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent implements OnInit {
  Form: FormGroup;

  file: File | null = null;
  titre: string = '';
  description: string = '';
  lieu: string = '';
  deadline: string = '';
  nombreplaces: number = 0;
  link: string = '';
offre = new offre(0,'','','', new Date(),0,'','')
  constructor(private route: ActivatedRoute,private snackBar: MatSnackBar,private offreService: OffreService,private location: Location,private formBuilder: FormBuilder) {
    this.Form = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      lieu: ['', Validators.required],
      deadline: ['', Validators.required],
      nombreplaces: ['', [Validators.required, Validators.min(1)]],
      link: ['', [Validators.required, Validators.pattern(/^https?:\/\//i)]],
    });
  }
  ngOnInit(): void {

  }
  handleFileInput(event: any) {
    this.file = event.target.files[0];
  }
  private showSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 10000, // Duration in milliseconds
      verticalPosition: 'top', // Position at the top of the screen
      horizontalPosition: 'center' // Centered horizontally
    });
  }

  submitForm() {
    const currentDate = new Date();
    const deadlineDate = new Date(this.deadline);

    if (deadlineDate <= currentDate) {
      console.error('Invalid Date.');
      this.showSnackBar('Deadline doit être dans le futur', 'Error');
    
      return;
      
    }
    const dateString = new Date(this.deadline).toISOString().split('T')[0];

    this.offreService
      .SaveOffre(this.file, this.titre, this.description, this.lieu, dateString, this.nombreplaces, this.link)
      .subscribe(
        (offre) => {
          // Handle success response
          console.log('Offre saved:', offre);
        },
        (error) => {
          // Handle error
          console.error('Error saving offre:', error);
        }
      );
      this.location.replaceState('/admin/Home');
      location.reload();
  }

}