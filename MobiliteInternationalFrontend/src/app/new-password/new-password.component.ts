import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../core/service/user.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent {
  constructor(private userService: UserService, private route: Router) { }
  idUser: any; // Set the user ID.
  token: any; // Set the token received during the password reset request.
  newpassword:any;
  email: any; // Set the new password.
  resetPassword(): void {
    this.userService.ResetPass(this.idUser,this.token,this.newpassword)
      .subscribe(
        (response: any) => {
          // Handle the response from the server if necessary.
          console.log('Password reset successful.');
        },
        (error: any) => {
          // Handle errors, if any.
          console.error('Password reset failed:', error);
        }
      );
      this.route.navigate(['Signin']);
  }
}
