import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class StoreSellerPersonService {

  jwtHelper = new JwtHelperService();

  tokenExpired: boolean = false;

  blocked: boolean = false;


  constructor() {
    this.setInterval()
  }

  public setInterval() {

    setInterval(() => {

      // let tokenStore = this.getStoreItem('auth-user')!;
      let tokenStore = this.getStoreItem('auth-seller-person')!;

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

    window.localStorage.removeItem('auth-seller-person');

    window.localStorage.setItem('auth-seller-person', accessToken);

  }

  public isTokenExpired(): boolean {

    let tokenStore = this.getStoreItem('auth-seller-person')!;

    if (this.getStoreItem('auth-seller-person') === null) {
      // Токена нет
      return false;
    } else {
      if (this.jwtHelper.isTokenExpired(tokenStore)) {
        // Токен истёк
        // this.removeStoreItem('auth-user');
        this.removeStoreItem('auth-seller-person');
        return false;
      } else {
        // Токен не истёк
        return true;
      }
    }
  }



  public isBlocked() {
    return this.blocked;
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
