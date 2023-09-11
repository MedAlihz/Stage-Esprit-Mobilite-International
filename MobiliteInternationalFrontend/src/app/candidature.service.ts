import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidature } from './core/model/Candidature';

@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  private baseUrl = `http://localhost:8088/Candidature`;
  private apiUrl = 'http://localhost:8088/Candidature/AddCandidature/'
  constructor(private http: HttpClient) { }
  SubmitApplication(idOffre: number, candidature: Candidature): Observable<Candidature> {
    const url = `${this.baseUrl}/AddCandidature/${idOffre}`;
    return this.http.post<Candidature>(url, candidature);
    
  }
  public addCandidature(candidature: Candidature){
    return this.http.post("http://localhost:8088/Candidature/AddCandidature/",candidature,{responseType:'text' as 'json'});

  } 
  GetCandidature(Iduser: number) {
    return this.http.get<Candidature>(`http://localhost:8088/Candidature/Show-UserCandidature/${Iduser}`);      
  }
 /* GeneratePDF(idCandidature: number) {
    return this.http.get(` http://localhost:8088/Candidature/generate/${idCandidature}`);      
  }
  */
  generatePdf(idCandidature: number): Observable<Blob> {
    const url = `http://localhost:8088/Candidature/generate/${idCandidature}`;
    const headers = new HttpHeaders({
      Accept: 'application/pdf'
    });

    return this.http.get(url, {
      responseType: 'blob',
      headers
    });
  }
  GetTopCandidature(idOffre: number) {
    return this.http.get<Candidature>(`http://localhost:8088/Candidature/Show-Top/${idOffre}`);      
  }
  public ShowAllCandidature() {
    return this.http.get("http://localhost:8088/Candidature/Show-All");
    
  }
  public Delete(idCandidature: number) {
    return this.http.delete(` http://localhost:8088/Candidature/remove-Candidature/${idCandidature}`);
  }
  searchUserIdByEmail(email: string): Observable<number> {
    const apiUrl = 'http://localhost:8088/api/v1/Auth/';

    const url = `${apiUrl}SearchId/?Email=${email}`;
    return this.http.get<number>(url);
  }
  public Assign(idCandidature: number,idUser:number) {
    return this.http.post<void>(
      `${this.baseUrl}/assign-idCandidature-to-user/${idCandidature}/${idUser}`,
      {}
    );
  }
  public Send(idOffre: number) {
    return this.http.post(
      `${this.baseUrl}/send/${idOffre}`,
      {}
    );
  }
  isValidEmail(email: string): Observable<boolean> {
    const url = `http://localhost:8088/Candidature/validEmail/${email}`;
    return this.http.get<boolean>(url);
  }
  
}
