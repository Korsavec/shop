import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class LocalStoreService {

  jwtHelper = new JwtHelperService();

  tokenExpired: boolean = false;


  constructor() {
    this.setInterval()
  }

  public setInterval() {

    setInterval(() => {

      let tokenStore = this.getStoreItem('auth-user')!;

      if (tokenStore === null) {
        // Токена нет
        this.tokenExpired = false;
      } else {
        if (this.jwtHelper.isTokenExpired(tokenStore)) {
          // Токен истёк
          this.removeStoreItem('auth-user');
          this.tokenExpired = false;
        } else {
          // Токен не истёк
          this.tokenExpired = true;

        }
      }
    }, 60000);

  }

  public saveJwt(jwt: any): void {

    let accessToken = jwt;

    window.localStorage.removeItem('auth-user');

    window.localStorage.setItem('auth-user', accessToken);

  }

  public isTokenExpired(): boolean {

    let tokenStore = this.getStoreItem('auth-user')!;

    if (this.getStoreItem('auth-user') === null) {
      // Токена нет
      return false;
    } else {
      if (this.jwtHelper.isTokenExpired(tokenStore)) {
        // Токен истёк
        this.removeStoreItem('auth-user');
        return false;
      } else {
        // Токен не истёк
        return true;
      }
    }
  }


  public removeCount() {
    window.localStorage.removeItem('counterLogin');
    window.localStorage.removeItem('timeLogin');
  }

  public removeStoreItem(key: string) {
    return window.localStorage.removeItem(key);
  }

  public getStoreItem(key: string):string | null {
    return window.localStorage.getItem(key);
  }

}
