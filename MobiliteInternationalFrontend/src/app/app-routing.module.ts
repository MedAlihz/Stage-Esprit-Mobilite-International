import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AllTemplateUserComponent } from 'src/frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from 'src/frontOffice/body-user/body-user.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { MailconfirmationComponent } from './core/mailconfirmation/mailconfirmation.component';
import { OffreComponent } from './offre/offre.component';
import { HomeComponent } from './home/home.component';
import { CandidatureFormComponent } from './candidature-form/candidature-form.component';
import { ListOfferComponent } from './list-offer/list-offer.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { TopConidatureComponent } from './top-conidature/top-conidature.component';
import { ListcandidatureComponent } from './listcandidature/listcandidature.component';
import { AllTemplateUser2Component } from 'src/frontOfficeAdmin/all-template-user2/all-template-user2.component';
import { BodyUser2Component } from 'src/frontOfficeAdmin/body-user/body-user2.component';
import { AllTemplateAdminComponent } from 'src/backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from 'src/backOffice/body-admin/body-admin.component';
import { EmailsComponent } from './emails/emails.component';
import { UserManagementComponent } from './user-management/user-management.component';



const routes: Routes = [
  // { path: "", redirectTo: "admin", pathMatch: "full" },
  { path: "Signin", component: SigninComponent },
  { path: "SignUp", component: SignupComponent },
  { path: "reset-password", component: ResetPasswordComponent },
  { path: "new-password", component: NewPasswordComponent },

  {path:"Confirmation",component:MailconfirmationComponent},
  //{ path: 'Offre/:id', component: ApplicationFormComponent },

  

  {
    path: 'Dashboard', component: AllTemplateAdminComponent,
    children: [
      {
        path: "",
        component: BodyAdminComponent
      },
      {path:"Home",component:HomeComponent},
      { path: "TopCandidature", component: TopConidatureComponent },
    ]
  },
  {
    path: 'admin', component: AllTemplateUser2Component,
    children: [
      {
        path: "",
        component: BodyUser2Component
      },
     {path:"AjouterMobilite",component:OffreComponent},
      {path:"Home",component:HomeComponent},
      { path: "TopCandidature", component: TopConidatureComponent },
      { path: "SendEmails", component: EmailsComponent },
      { path: "UserManagement", component: UserManagementComponent },
    
    ]
  },
  {
    path: 'user', component: AllTemplateUserComponent,
    children: [
      {
        path: "",
        component: BodyUserComponent
      },
      { path: "ListCandidature", component: ListcandidatureComponent },
      { path: "ListOffers", component: ListOfferComponent },
      { path: "Postuler", component: CandidatureFormComponent },
    ]
  },
 
  
  

  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
