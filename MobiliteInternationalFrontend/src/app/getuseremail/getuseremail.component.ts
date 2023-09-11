import { Component, OnInit } from '@angular/core';
import { UserService } from '../core/service/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-getuseremail',
  templateUrl: './getuseremail.component.html',
  styleUrls: ['./getuseremail.component.css']
})
export class GetuseremailComponent{
  userRole: string;
  error: string;

  email : string;
  message:any;
  constructor(
    private service : UserService,
  ){}
  
  GetEmail(){
    let resp=this.service.searchUserRoleByEmail(this.email);
    resp.subscribe(
      role => {
        this.userRole = role; // Since the backend returns a plain string, no JSON.parse() needed
        console.log('User Role:', this.userRole);

        // Proceed with the rest of the login process...
      },
      error => {
        this.error = error;
        console.log('Error:', error);
      }
    );
  }
} 
