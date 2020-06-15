import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';
import { AuthenticateService } from 'src/app/service/auth/authenticate.service';
import { UserService } from 'src/app/service/user/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  showError: boolean = false;
  user: User;

  userLoginForm = this.fb.group({
    username: ['', Validators.required],
    password: [null, Validators.required, Validators.minLength(5)]
  });
  

  constructor(private fb: FormBuilder, private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (!this.userLoginForm.invalid) {
    
    // login returns jwt
    this.userService.login(this.userLoginForm.value).subscribe(
      res => {
        localStorage.setItem('token', JSON.stringify(res));
        localStorage.setItem('username', this.userLoginForm.value.username);
        
        // get current user and set user id in the local storage
        this.getCurrentUser();
        
        this.showError = false;
        this.router.navigate(['/home']);
      },
      err => {
        console.log(err)
        this.showError = true;
      }
    ) 
  }
  
  }
  getCurrentUser(){
    this.userService.getUser(this.userLoginForm.value.username).subscribe(
      data => {
        this.user = data;
        localStorage.setItem('user_id', this.user.users_id);
      }) 
  }
}
