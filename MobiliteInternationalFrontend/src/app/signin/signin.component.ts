import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../core/service/user.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  error: any;
  email!: string;
  password!: string;
  userRole: string;

  displaymenu=false;
  displayemployee=false;
  displayuser=false;
  currentrole:any;
  idUser: any; // Set the user ID.
  token: any; // Set the token received during the password reset request.
  newPassword: any; // Set the new password.
  constructor(private userService: UserService, private route: Router) { }

  ngOnInit(): void {

  }








  //request authentication
 /*login() {
    const data = {
      email: this.email,
      password: this.password
    };

    // this.userService.searchRoleByEmail(data.email).subscribe(role => {
    //   this.userRole = role;
    //   console.log(`User Role: ${role}`);
    // });

  

    //authentication response ??
    console.log(data)
    this.userService.login(data).subscribe(
      response => {
       
        this.userService.setToken(response.token);
        this.route.navigate(['admin/AjouterMobilite']);
        console.log(response.token);

      },
      (error) => {
        this.error = error;
        console.log(error);
      }
    );
  }
 
  /*login() {
    const data = {
      email: this.email,
      password: this.password
    };

    // Request authentication response
    this.userService.login(data).subscribe(
      response => {
        this.userService.setToken(response.token);
        this.currentrole = response.role; // Store the user's role from the response.
        console.log(response.token);

        // Based on the user's role, set the appropriate boolean flags for displaying menus/components
        this.displaymenu = true;
        if (this.currentrole === 'Admin') {
          this.displayemployee = true;
          this.displayuser = true;
        } else if (this.currentrole === 'Etudiant') {
          this.displayuser = true;
        }

        // Navigate to the appropriate page based on the user's role
        if (this.currentrole === 'Admin') {
          this.route.navigate(['user/AjouterMobilite']); // Replace 'admin-dashboard' with the path for the admin dashboard.
        } else if (this.currentrole === 'Etudiant') {
          this.route.navigate(['user-dashboard']); // Replace 'user-dashboard' with the path for the user dashboard.
        } else {
          // Handle other roles or error cases as needed.
        }

      },
      (error) => {
        this.error = error;
        console.log(error);
      }
    );
  }

 
*/

login() {
  const data = {
    email: this.email,
    password: this.password
  };
 
  
  this.userService.searchUserRoleByEmail(data.email).subscribe(
    role => {
      this.userRole = role; 
      console.log('User Role:', this.userRole);
    

      this.userService.login(data).subscribe(
        response => {
         
          this.userService.setToken(response.token);
          if(this.userRole=='Admin'){
          this.route.navigate(['admin/AjouterMobilite']);
         console.log(response.token);   
        }
      else{
        this.route.navigate(['user/ListOffers']);
        console.log(response.token);  
      }},
      ); 
      this.userService.searchUserIdByEmail(data.email).subscribe(
        id => {
          this.idUser = id; 
          console.log('User id:', this.idUser);
    })
       },
    error => {
      this.error = error;
      console.log('Error:', error);
    }
  );
  console.log(data)

}






}
