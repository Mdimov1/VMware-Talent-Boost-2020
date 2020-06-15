import { Component, OnInit, Input } from '@angular/core';
import { Charity } from 'src/app/models/charity';
import { ActivatedRoute, Router } from '@angular/router';
import { CharityService } from 'src/app/service/charity.service';
import { AuthenticateService } from 'src/app/service/auth/authenticate.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-charity-details',
  templateUrl: './charity-details.component.html',
  styleUrls: ['./charity-details.component.scss']
})

export class CharityDetailsComponent implements OnInit {
  charity: Charity;

  // Properties to manage a charity details
  charity_id_from_url: number;
  author_name: string;
  count_participants: number;
  donated_amount: number;

  // Properties to manage forms
  donateModal = false;
  volunteerModal = false;
  deleteModal = false;
  donateAmount: number;

  constructor(private activatedRoute: ActivatedRoute, 
    private charityService: CharityService,
    private authService: AuthenticateService,
    private router: Router) { 
  }

  ngOnInit(): void {
    // Get current charity id from the url
    this.activatedRoute.params.subscribe(params => {
      this.charity_id_from_url = params['charitiesId'];
      });

      this.getCharity();
  }

  //Get charity data and call other methods
  getCharity(){

  this.charityService.getCharityById(this.charity_id_from_url).subscribe(data =>{
      this.charity = data;

      this.getAuthorName();
      this.getCountOfCharityParticipants();
      this.getDonatedAmount();
    });
  }

  //Get author name
  getAuthorName(){
    this.charityService.getCharityAuthor(this.charity.charities_id)
    .subscribe(data =>{
      this.author_name = data;
    });
  }

  //Get count of participants
  getCountOfCharityParticipants(){
    this.charityService.getCountOfParticipants(this.charity.charities_id)
    .subscribe(data =>{
      this.count_participants = data;
    })
  }

  //get donated amount
  getDonatedAmount(){
    this.charityService.getDonatedAmount(this.charity.charities_id)
    .subscribe(data =>{
      this.donated_amount = data;
    })
  }

  //Show donate and volunteer if is authenticated user
  authenticated(){
    return this.authService.authenticated();
  }

  //Donate amount to the charity
  donate(){
    this.charityService.donateAmount(this.charity.charities_id, this.donateAmount);
    window.location.reload();
  }

  //Add user to participants in the charity
  addVolunteer(){
    const user_id = Number(localStorage.getItem('user_id'));
    this.charityService.addParticipant(this.charity.charities_id, user_id);
    window.location.reload();
  }

  canDelete(){
    const user_id = Number(localStorage.getItem('user_id'));
    return (user_id === this.charity.author_id) ? true : false;
  }

  deleteCharity(){
    this.charityService.deleteCharity(this.charity_id_from_url);
    this.router.navigate([('/home')]);
  }
}
