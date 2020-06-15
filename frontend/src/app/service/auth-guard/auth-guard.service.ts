import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticateService } from '../auth/authenticate.service';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class AuthGuardService implements CanActivate{

  constructor(private router: Router, private authService: AuthenticateService) { }

  canActivate(): boolean{
    if(!this.authService.authenticated()){
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
