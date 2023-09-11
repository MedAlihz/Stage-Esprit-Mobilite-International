import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { CandidatureService } from '../candidature.service';
import { offre } from '../core/model/offre';
import { OffreService } from '../offre.service';
import { Candidature } from '../core/model/Candidature';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-top-conidature',
  templateUrl: './top-conidature.component.html',
  styleUrls: ['./top-conidature.component.css']
})
export class TopConidatureComponent   implements OnInit {
  //topCandidature: Candidature | null = null; // Initialize topCandidature as null or an appropriate default value
  loading = false;
  error = '';
  topCandidatures: Candidature[] = []; // Initialize topCandidature as an empty array
  transportlist: Candidature[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: CandidatureService,
    private snackBar: MatSnackBar
    ) { }

  
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const idOffre = Number(params.get('idOffre'));
      if (!isNaN(idOffre)) {
        this.getTopCandidature(idOffre);
      } else {
        this.error = 'Invalid idOffre parameter.';
      }
    });
  }
  private showSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 10000, // Duration in milliseconds
      verticalPosition: 'top', // Position at the top of the screen
      horizontalPosition: 'center' // Centered horizontally
    });
  }
  getTopCandidature(idOffre: number){
    this.service.GetTopCandidature(idOffre).subscribe((data) => {
      this.topCandidatures = data as unknown as Candidature[];
    },
      (error: any) => {
        this.error = 'Failed to fetch top candidature.';
        this.loading = false;
      }
    );
  }
  
  /*sendEmails() {
    const idOffre = Number(this.route.snapshot.queryParamMap.get('idOffre'));
    this.service.Send(idOffre).subscribe(
      () => {
        console.log('Emails sent successfully');
      },
      (error) => {
        console.error('Error sending emails:', error);
      }
    );
  }
  */

  sendEmails() {
    const idOffre = Number(this.route.snapshot.queryParamMap.get('idOffre'));
    console.log(idOffre);
  
      this.service.Send(idOffre).subscribe(
        () => {
          console.log('Emails sent successfully');
          this.showSnackBar('Emails sent successfully', 'Success');

        },
        (error) => {
          console.error('Error sending emails:', error);
          this.showSnackBar('Error sending emails', 'Error');
        }
      );
   
  }

  /*search(idOffre:number){
    this.service.GetTopCandidature(idOffre).subscribe((data) => {
      this.topCandidatures = data as unknown as Candidature[];
    });
   }
*/
}
