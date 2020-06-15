import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClarityModule } from '@clr/angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterFormComponent } from './forms/register-form/register-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginFormComponent } from './forms/login-form/login-form.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { CharityCardComponent } from './components/charity-card/charity-card.component';
import { CharityListComponent } from './components/charity-list/charity-list.component';
import { CharityDetailsComponent } from './components/charity-details/charity-details.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { Interceptor } from './interceptor/interceptor';
import { ProfileComponent } from './components/profile/profile.component';
import { CreateCharityComponent } from './forms/create-charity/create-charity.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterFormComponent,
    LoginFormComponent,
    NavbarComponent,
    HomeComponent,
    CharityCardComponent,
    CharityListComponent,
    CharityDetailsComponent,
    NotFoundComponent,
    ProfileComponent,
    CreateCharityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClarityModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
