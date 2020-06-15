import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Charity } from 'src/app/models/charity';
import { stringify } from 'querystring';
import { Router } from '@angular/router';

@Component({
  selector: 'app-charity-card',
  templateUrl: './charity-card.component.html',
  styleUrls: ['./charity-card.component.scss']
})

export class CharityCardComponent implements OnInit {
  @Input() charity: Charity;
  
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  //Sent current charity to charity-details.component.ts
  onSelect(){
      this.router.navigate(['/charity', this.charity.charities_id]);
  }
}
