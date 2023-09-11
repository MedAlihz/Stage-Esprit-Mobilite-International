import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../core/service/user.service';
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  constructor(private userService: UserService, private route: Router) { }
  idUser: any; // Set the user ID.
  token: any; // Set the token received during the password reset request.
  email: any; // Set the new password.
  Sendresetpass(): void {
    this.userService.SendReset(this.email)
    .subscribe(
      response => {
        this.route.navigate(['new-password']);
     

      },
      (error) => {
      
        console.log(error);
      }
    );
    this.route.navigate(['new-password']);
  }
}
