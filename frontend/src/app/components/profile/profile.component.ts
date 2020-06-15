import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    const username = localStorage.getItem('username');
    this.userService.getUser(username).subscribe(data => {
      this.user = data;
    })
  }


}
