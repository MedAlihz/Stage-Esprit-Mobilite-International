import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { offre } from './core/model/offre';
import { Observable, map } from 'rxjs';
import { Candidature } from './core/model/Candidature';
@Injectable({
  providedIn: 'root'
})
export class OffreService {
  private apiUrl = 'http://localhost:8088/Offre/retrieve-all-Offre';

  constructor(private http:HttpClient) { }
  public AddOffre(offre: offre){
    return this.http.post("http://localhost:8088/Offre/AddOffre",offre,{responseType:'text' as 'json'});

  } 
  public SaveOffre(file: File, titre: string, description: string, lieu: string, dateString: string, nombrePlaces: number, link: string): Observable<offre> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('titre', titre);
    formData.append('description', description);
    formData.append('lieu', lieu);
    formData.append('deadline', dateString);
    formData.append('nombrePlaces', String(nombrePlaces));
    formData.append('link', link);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');

    return this.http.post<offre>("http://localhost:8088/Offre/AddF", formData, { headers });
  }
  public FindOffers() {
    return this.http.get("http://localhost:8088/Offre/retrieve-all-Offre");
  }
  public Delete(id: number) {
    return this.http.delete(`http://localhost:8088/Offre/remove-Offre/${id}`);
  }
  show(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  /*public UpdateOffre(offre: offre){
    return this.http.put("http://localhost:8088/Offre/update-Offre",offre,{responseType:'text' as 'json'});

  } */
  /*UpdateOffre(idOffre: number, updatedEntity: any): Observable<any> {
    return this.http.put(`http://localhost:8088/Offre/update-Offre/${idOffre}`, updatedEntity);
  }*/
  UpdateOffre(idOffre: number,file: File, titre: string, description: string, lieu: string, dateString: string, nombrePlaces: number, link: string): Observable<offre> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('titre', titre);
    formData.append('description', description);
    formData.append('lieu', lieu);
    formData.append('deadline', dateString);
    formData.append('nombrePlaces', String(nombrePlaces));
    formData.append('link', link);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    return this.http.put<offre>(`http://localhost:8088/Offre/update-Offre/${idOffre}`, formData, { headers });
  }
  
    public getOfferById(id: number): Observable<offre> {
      return this.http.get<offre>(`http://localhost:8088/Offre/get-Offre/${id}`);
    }
    
  /*public getOfferById(id: number): Observable<offre> {
    return this.http.get<offre>(`http://localhost:8088/Offre/get-Offre/${id}`)
      .pipe(map((offer: offre) => {
        // Convert the deadline string to a Date object
        offer.deadline = new Date(offer.deadline);
        return offer;
      }));
  }*/
  public addCandidature(candidature: Candidature){
    return this.http.post("http://localhost:8088/Candidature/AddCandidature/",candidature,{responseType:'text' as 'json'});

  } 
  public SendEmails(text:string,sub:string){

    return this.http.post(`http://localhost:8088/Offre/SendEmails?text=${text}&Subject=${sub}`,{responseType:'text' as 'json'});
  }

}
