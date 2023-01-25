import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  jwtHelper = new JwtHelperService();

  tokenExpired: boolean = false;


  constructor() {
    this.setInterval()
  }

  public setInterval() {

    setInterval(() => {

      let tokenStorage = this.getStorageItem('auth-logistics-company')!;

      if (tokenStorage === null) {
        // Токена нет
        this.tokenExpired = false;
      } else {
        if (this.jwtHelper.isTokenExpired(tokenStorage)) {
          // Токен истёк
          this.removeStorageItem('auth-logistics-company');
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

    window.localStorage.removeItem('auth-logistics-company');

    window.localStorage.setItem('auth-logistics-company', accessToken);

  }

  public isTokenExpired(): boolean {

    let tokenStorage = this.getStorageItem('auth-logistics-company')!;

    if (this.getStorageItem('auth-logistics-company') === null) {
      // Токена нет
      return false;
    } else {
      if (this.jwtHelper.isTokenExpired(tokenStorage)) {
        // Токен истёк
        this.removeStorageItem('auth-logistics-company');
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

  public removeStorageItem(key: string) {
    return window.localStorage.removeItem(key);
  }

  public getStorageItem(key: string):string | null {
    return window.localStorage.getItem(key);
  }


}
