import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthServiceService {

  constructor() { }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles') as any);
  }
}
