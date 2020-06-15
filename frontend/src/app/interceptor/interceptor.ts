import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { Injector, Injectable } from '@angular/core';
import { AuthenticateService } from '../service/auth/authenticate.service';

@Injectable()
export class Interceptor implements HttpInterceptor{

    constructor(private auth: AuthenticateService){}
    
    intercept(request: HttpRequest<any>, next: HttpHandler){

        let token = this.auth.getToken();

        // Set token to the request
        if(token){
            request = request.clone({
                headers: request.headers.set('Authorization', `Bearer ${this.auth.getToken()}`)
            });
        }
        return next.handle(request);
    }
}


