import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role, user } from '../core/model/user';
import { UserService } from '../core/service/user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {


  constructor(private userService: UserService, private currentRoute: ActivatedRoute, private route: Router,private location: Location) { }

  User : user = new user(
    "",
  "",
  "",
  "",
  Role.Admin
  );

  action: string = '';

  message:any;

  ngOnInit(): void {

 
  }
  
  Register(){
    let resp=this.userService.AddUser(this.User);
    resp.subscribe((data) => {
      this.message = data;
      this.location.replaceState('/Confirmation');
      location.reload();
    });
  }

}
