import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AllTemplateUserComponent } from 'src/frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from 'src/frontOffice/body-user/body-user.component';
import { FooterUserComponent } from 'src/frontOffice/footer-user/footer-user.component';
import { HeaderUserComponent } from 'src/frontOffice/header-user/header-user.component';
import { OffreComponent } from './offre/offre.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { ListOfferComponent } from './list-offer/list-offer.component';
import { CandidatureFormComponent } from './candidature-form/candidature-form.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { TopConidatureComponent } from './top-conidature/top-conidature.component';
import { ListcandidatureComponent } from './listcandidature/listcandidature.component';
import { AllTemplateUser2Component } from 'src/frontOfficeAdmin/all-template-user2/all-template-user2.component';
import { BodyUser2Component } from 'src/frontOfficeAdmin/body-user/body-user2.component';
import { FooterUser2Component } from 'src/frontOfficeAdmin/footer-user/footer-user2.component';
import { HeaderUser2Component } from 'src/frontOfficeAdmin/header-user/header-user2.component';
import { GetuseremailComponent } from './getuseremail/getuseremail.component';
import { AllTemplateAdminComponent } from 'src/backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from 'src/backOffice/body-admin/body-admin.component';
import { FooterAdminComponent } from 'src/backOffice/footer-admin/footer-admin.component';
import { HeaderAdminComponent } from 'src/backOffice/header-admin/header-admin.component';
import { SideAdminComponent } from 'src/backOffice/side-admin/side-admin.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { EmailsComponent } from './emails/emails.component';
import { UserManagementComponent } from './user-management/user-management.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderAdminComponent,
    FooterAdminComponent,
    SideAdminComponent,
    AllTemplateAdminComponent,
    BodyAdminComponent,
    HeaderUserComponent,
    BodyUserComponent,
    FooterUserComponent,
    AllTemplateUserComponent,
    SigninComponent,
    SignupComponent,
    OffreComponent,
    HomeComponent,
    ListOfferComponent,
    CandidatureFormComponent,
    ResetPasswordComponent,
    NewPasswordComponent,
    TopConidatureComponent,
    ListcandidatureComponent,
    HeaderUser2Component,
    BodyUser2Component,
    FooterUser2Component,
    AllTemplateUser2Component,
    GetuseremailComponent,
    EmailsComponent,
    UserManagementComponent,
    
 

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCardModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
