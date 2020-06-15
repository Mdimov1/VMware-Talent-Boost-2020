import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../service/auth/authenticate.service';
import { UserService } from '../service/user/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  loginUser: string;
  logoutModal = false;

  constructor(private authService: AuthenticateService, private userService: UserService) {   }

  ngOnInit(): void {
    this.loginUser= localStorage.getItem('username');
  }

  authenticated(){
    return this.authService.authenticated();
  }
  
  logout(){
    this.userService.logout();
  }
}
