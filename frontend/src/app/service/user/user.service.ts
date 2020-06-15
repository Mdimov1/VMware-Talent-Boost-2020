import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  url = 'http://localhost:8080'
  
  constructor(private http: HttpClient, private router: Router) { }

  //login 
  login(authRequest){
    return this.http.post(`${this.url}/authenticate`, authRequest, {responseType: 
  'text'});
  }

  //logout
  logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    window.location.reload();
  }
  
  //get all users from the backend
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/users`);
  } 

  //register new user
  addUser(user: User) {
    this.http.post(`${this.url}/register`, user).subscribe(data =>{
      this.router.navigate(['/login']);
    },
    err =>{
      window.alert("Invalid data. It is possible that a username already exists! Please, try again!");
    });
  }

  // Get user by username
  getUser(username: string): Observable<User>{
    return this.http.get<User>(`${this.url}/users/${username}`, {responseType: 'json'});
  }

  //Welcome message for test
  welcome(): Observable<string> {
    return this.http.get<string>(`${this.url}/profile`, {responseType: 'text' as 'json'});
  }
}
