import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OffreService } from '../offre.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-emails',
  templateUrl: './emails.component.html',
  styleUrls: ['./emails.component.css']
})
export class EmailsComponent {
sub: string = '';
  text: string = '';
  data : any;
  constructor(private route: ActivatedRoute,private offreService: OffreService,private location: Location) {
  
  }
  submitForm() {

    this.offreService
      .SendEmails(this.text, this.sub)
      .subscribe(
        (data) => {
          // Handle success response
          console.log('Emails Sent:', data);
        },
        (error) => {
          // Handle error
          console.error('Error sending emails:', error);
        }
      );
      this.location.replaceState('/admin/Home');
      location.reload();
  }

}
