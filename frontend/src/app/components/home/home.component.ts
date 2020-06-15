import { Component, OnInit, Input } from '@angular/core';
import { CharityService } from 'src/app/service/charity.service';
import { Charity } from 'src/app/models/charity';
import { AuthenticateService } from 'src/app/service/auth/authenticate.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  filterTitle: string;
  charities: Charity[];
  authenticated: boolean;

  constructor(private charityService: CharityService, private auth: AuthenticateService) {
    this.getAllCharities();
   }

  ngOnInit(): void {
    this.authenticated = this.auth.authenticated();
  }

  onSubmit(){
    if(this.filterTitle === undefined || this.filterTitle == null || this.filterTitle===""){
      this.getAllCharities();
    }else{
      this.filterFunc();
    }
  }

  filterFunc() {  
    this.charityService.getCharitiesByTitle(this.filterTitle).subscribe(
      data =>{
      this.charities = data;
    });
  }

  getAllCharities(){
    this.charityService.getCharities().subscribe(data =>{
      this.charities = data;
    });
  }

}
