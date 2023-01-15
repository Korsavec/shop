import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {properties} from "../../resources/application.properties";
import {StoreUserService} from "../_service/user/store-user.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private host: string = properties.apiUrl;

  constructor(private storeUserService: StoreUserService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {



    if (request.url.includes(`${this.host}/api/auth/registrationUser`)
      || request.url.includes(`${this.host}/api/auth/registrationSellerPerson`)
      || request.url.includes(`${this.host}/api/auth/confirmEmailUser`)
      || request.url.includes(`${this.host}/api/auth/confirmEmailSellerPerson`)
      || request.url.includes(`${this.host}/api/auth/resetPasswordUser`)
      || request.url.includes(`${this.host}/api/auth/resetPasswordSellerPerson`)
      || request.url.includes(`${this.host}/api/auth/checkServerTokenUserResetPassword`)
      || request.url.includes(`${this.host}/api/auth/checkServerTokenSellerPersonResetPassword`)
      || request.url.includes(`${this.host}/api/auth/newPasswordUser`)
      || request.url.includes(`${this.host}/api/auth/newPasswordSellerPerson`)
      || request.url.includes(`${this.host}/api/auth/loginUser`)
      || request.url.includes(`${this.host}/api/auth/loginSellerPerson`)
      || request.url.includes(`${this.host}/api/auth/checkShopNameRegistrationSellerPersonRequest`)
      || request.url.includes(`${this.host}/api/all`)
      || request.url.includes(`${this.host}/resources/all`)
      || request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
      return next.handle(request);
    }

    // Для auth-user
    if (request.url.includes(`${this.host}/api/AccountGuard/user`)) {
      const token = this.storeUserService.getStoreItem('auth-user');

      if (token) {
        const modifiedQuery = request.clone({
          setHeaders: {
            'Authorization': `Bearer ${token}`,
          }
        });

        return next.handle(modifiedQuery);

      } else {

        return next.handle(request);

      }
    }



    // Для пользователя
    if (request.url.includes(`${this.host}/api/AccountGuard/seller`)) {
      const token = this.storeUserService.getStoreItem('auth-seller-person');

      if (token) {
        const modifiedQuery = request.clone({
          setHeaders: {
            'Authorization': `Bearer ${token}`,
          }
        });

        return next.handle(modifiedQuery);

      } else {

        return next.handle(request);

      }
    }
    return next.handle(request);


  }
}
