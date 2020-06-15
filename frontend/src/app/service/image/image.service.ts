import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  url = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

 // Upload image
 uploadImage(imageFile: any){
  this.http.post(`${this.url}/upload/image`, imageFile).subscribe();
 }
}