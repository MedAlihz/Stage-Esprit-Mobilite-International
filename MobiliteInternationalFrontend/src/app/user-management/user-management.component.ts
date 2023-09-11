import { Component, OnInit } from '@angular/core';
import { UserService } from '../core/service/user.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
users:any[];
showUpdateForm: boolean = false; // New variable to control form visibility
  updatedUser: any = {}; // Object to store updated user data

  constructor(
    private service : UserService,
  ) {}
  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.FindUsers().subscribe(data => {
      this.users = data as any[];
    });
  }

  deleteUser(idUser: number) {
    this.service.delete(idUser).subscribe(
      response => {
        console.log("User deleted successfully.");
        this.getUsers();
      },
      error => {
        console.log("Error while deleting User:", error);
      }
    );
  }

  // Method to toggle the update form visibility
  toggleUpdateForm(user: any) {
    this.showUpdateForm = !this.showUpdateForm;
    this.updatedUser = { ...user }; // Copy user data for editing
  }

  // Method to update user information
  updateUserInfo(updatedUser: any): void {
    this.service.updateUser(updatedUser.idUser, updatedUser).subscribe(
      (response) => {
        // Handle the successful response here
        console.log('User updated successfully', response);
        this.showUpdateForm = false; // Hide the form after updating
        this.getUsers(); // Refresh the user list
        window.location.reload(); // Reload the page
      },
      (error) => {
        // Handle any errors here
        console.error('Error updating user', error);
      }
    );
  }

  // Method to cancel the update operation and hide the form
  cancelUpdate() {
    this.showUpdateForm = false;
  }
}

