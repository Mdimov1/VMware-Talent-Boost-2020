import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {


  constructor(private http: HttpClient) {
  }

  getToken() {
    return localStorage.getItem('token');
  }

  authenticated(): boolean{
    return localStorage.getItem('token') ? true : false;
  }

  getUsername() {
    return localStorage.getItem('username');
  }
}
