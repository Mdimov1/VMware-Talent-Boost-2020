import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterFormComponent } from './forms/register-form/register-form.component';
import { LoginFormComponent } from './forms/login-form/login-form.component';
import { HomeComponent } from './components/home/home.component';
import { CharityDetailsComponent } from './components/charity-details/charity-details.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';

import { AuthGuardService } from './service/auth-guard/auth-guard.service';
import { CreateCharityComponent } from './forms/create-charity/create-charity.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'register', component: RegisterFormComponent},
  { path: 'login', component: LoginFormComponent},
  { path: 'createCharity', component: CreateCharityComponent, canActivate: [AuthGuardService] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService] },
  { path: 'charity/:charitiesId', component: CharityDetailsComponent},
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
