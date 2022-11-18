import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterPayload } from './register-payload';
import { map, Observable } from 'rxjs';
import { LoginPayload } from './login-payload';
import { JwtAuthResponse } from './jwt-auth-response';
import { LocalStorageService } from 'ngx-webstorage'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = 'http://localhost:8080/api/auth/';

  constructor(private httpClient: HttpClient, private localStoreService: LocalStorageService) { }

  register(registerPayload: RegisterPayload): Observable<any> {
    return this.httpClient.post(this.url + 'signup', registerPayload);
  }

  login(loginPayload: LoginPayload): Observable<boolean> {
    return this.httpClient.post<JwtAuthResponse>(this.url + 'login', loginPayload).pipe( map( data => {
      this.localStoreService.store('authenticationToken', data.authenticationToken);
      this.localStoreService.store('username', data.username)
      return true;
    }));
  }
}
