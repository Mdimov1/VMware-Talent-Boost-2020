import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent implements OnInit {
  users: User[];
  
  userRegisterForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.compose([Validators.required, Validators.minLength(5)])],
    name: ['', Validators.required],
    age: ['', Validators.required],
    gender: [''],
    location: ['']
  });

  constructor(private fb: FormBuilder,
    private route: ActivatedRoute, 
      private router: Router,
      private userService: UserService) {
      }

    ngOnInit(): void {
    }

  onSubmit() {
    if (!this.userRegisterForm.invalid) {
      this.userService.addUser(this.userRegisterForm.value);
  }
}

}
