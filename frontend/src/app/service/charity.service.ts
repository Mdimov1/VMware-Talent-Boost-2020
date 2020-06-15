import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Charity } from '../models/charity';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CharityService {
    url = 'http://localhost:8080/charity';
  
    constructor(private http: HttpClient) { }


    //get all charities
    getCharities(): Observable<Charity[]> {
      return this.http.get<Charity[]>(`${this.url}`);
    } 

    //get all charities by title
    getCharitiesByTitle(filterTitle: string): Observable<Charity[]> {
      return this.http.post<Charity[]>(`${this.url}/filtered`, filterTitle);
    } 

    //get all charities by id
    getCharityById(id: number): Observable<Charity> {
      return this.http.get<Charity>(`${this.url}/${id}`);
    }

    //get charity author
    getCharityAuthor(id: number): Observable<string> {
      return this.http.get(`${this.url}/${id}/author`, {responseType: 'text'});
    }

    //get participants in a charity
    getCountOfParticipants(id: number): Observable<number> {
      return this.http.get<number>(`${this.url}/${id}/participants`);
    }

    //get donated amount in a charity
    getDonatedAmount(id: number): Observable<number> {
      return this.http.get<number>(`${this.url}/${id}/donated`);
    }
    
    // Donate amount in a charity
    donateAmount(id: number, amount: number) {
      this.http.post(`${this.url}/${id}/donate`, amount).subscribe();
    }

    // Add participant in a charity
    addParticipant(charity_id: number, user_id: number) {
      this.http.post(`${this.url}/${charity_id}/participants`, user_id).subscribe();
    }

    // Add charity
    addCharity(charity: Charity){
      this.http.post(`${this.url}/add`, charity).subscribe();
    }

    // Delete charity
    deleteCharity(id: number){
      this.http.delete(`${this.url}/${id}/delete`).subscribe();
    }
    
}
