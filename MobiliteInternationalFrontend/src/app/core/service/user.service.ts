import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { user } from '../model/user';
import { Router } from '@angular/router';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private url = 'http://localhost:8088/api/v1/Auth';



  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Bearer' + localStorage.getItem('token')
    })
  };


  constructor(private http: HttpClient, private router: Router) { }
  apiUrl="http://localhost:8088/api/v1/Auth/"
  getAll(): Observable<user[]> {
    return this.http.get<user[]>("http://localhost:8088/api/v1/Auth/AllUsers");
  }
  public FindUsers() {
    return this.http.get("http://localhost:8088/api/v1/Auth/FindAllUsers");
  }
  public RemoveUser(id:number) {
    return this.http.delete(`http://localhost:8088/api/v1/Auth/RemoveUser/${id}`);
  }

  updateUser(idUser: number, updatedEntity: any): Observable<any> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/update-User/${idUser}`, updatedEntity);
  }
  public AddUser(user: user){
    return this.http.post("http://localhost:8088/api/v1/Auth/register",user,{responseType:'text' as 'json'});

  }
  

  SignUp(data: any): Observable<any> {
    return this.http.post(this.url + "/register", data)
  }
  login(data: any): Observable<any> {
    return this.http.post<{ token: string }>(this.url + "/Authen", data).pipe(
      map(result => {
        if (result && result.token) {
          localStorage.setItem('token', result.token);
          return true;
        }
        return false;
      })
    );

  }

  delete(idProjet: any): Observable<Object> {
    return this.http.delete(`http://localhost:8088/api/v1/Auth/DeleteUser/?id=${idProjet}`, this.httpOptions);
  }
  enable(idProjet: any): Observable<Object> {
    return this.http.put(`http://localhost:808/api/v1/Auth/Enable/${idProjet}`, this.httpOptions);
  }
  disable(idProjet: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/Disable/${idProjet}`, this.httpOptions);
  }
  confirm(token: any): Observable<Object> {
    return this.http.put(`http://localhost:808/api/v1/Auth/confirm?token=${token}`, this.httpOptions);
  }

  SendReset(email: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/ResetPass/InsetYourEmailHere/?email=${email}`, this.httpOptions);
  }
  ResetPass(idUser: any, token: any, password: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/ResetPass?idUser=${idUser}&token=${token}&neoPass=${password}`, this.httpOptions);
  }
  public getToken() {
    return localStorage.getItem('jwtToken');
  }
  public setToken(token: string) {

    localStorage.setItem('token', token);
    console.log(localStorage.getItem('jwtToken'));

  }

  /*GetRoleByemail(email :string):Observable<string>{
    return this.http.get<string>(`http://localhost:8088/api/v1/Auth/SearchRole/${email}`);
  } */

  GetRoleByemail(email: string): Observable<string> {
    const url = `http://localhost:8088/api/v1/Auth/SearchRole/?Email=${email}`;
    return this.http.get<string>(url);
  }



 /* searchUserRoleByEmail(email: string): Observable<string> {
    const apiUrl = 'http://localhost:8088/api/v1/Auth/';
    const url = `${apiUrl}SearchRole/?Email=${email}`;
    return this.http.get<string>(url);
  }*/
  searchUserRoleByEmail(email: string): Observable<string> {
    const apiUrl = 'http://localhost:8088/api/v1/Auth/';

    const url = `${apiUrl}SearchRole/?Email=${email}`;
    return this.http.get(url, { responseType: 'text' });
  }
  searchUserIdByEmail(email: string): Observable<string> {
    const apiUrl = 'http://localhost:8088/api/v1/Auth/';

    const url = `${apiUrl}SearchId/?Email=${email}`;
    return this.http.get(url, { responseType: 'text' });
  }
  searchUserByEmail(email: string): Observable<string> {
    const apiUrl = 'http://localhost:8088/api/v1/Auth/';
    const url = `${apiUrl}SearchUserByEmail/?Email=${email}`;
    return this.http.get(url, { responseType: 'text' });
  }


  // logout(): void {
  //   localStorage.removeItem('token');
  // }

  // isLoggedIn(): boolean {
  //   const token = localStorage.getItem('token');
  //   if (token) {
  //     const decodedToken = jwt_decode(token);
  //     const expirationDate = new Date(decodedToken.exp * 1000);
  //     return expirationDate > new Date();
  //   }
  //   return false;
  // }


  // getRole(): string {
  //   // Get the JWT token from local storage
  //   const token = localStorage.getItem('token');

  //   // Extract the payload from the token (i.e. everything between the first and second dots)
  //   const payload = token.split('.')[1];

  //   // Decode the payload from base64 encoding
  //   const decodedPayload = JSON.parse(atob(payload));

  //   // Get the user's role
  //   const userRole = decodedPayload.role;
  //   return userRole;
  // }


}
