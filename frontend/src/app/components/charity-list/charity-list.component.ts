import { Component, OnInit, Input } from '@angular/core';
import { Charity } from 'src/app/models/charity';
import { CharityService } from 'src/app/service/charity.service';

@Component({
  selector: 'app-charity-list',
  templateUrl: './charity-list.component.html',
  styleUrls: ['./charity-list.component.scss']
})
export class CharityListComponent implements OnInit {
  @Input() charities: Charity[];
  
  constructor(private charityService: CharityService) { 
  }

  ngOnInit(): void {
    
  }

}
