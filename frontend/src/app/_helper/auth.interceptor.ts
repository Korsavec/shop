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

    if (request.url.includes(`${this.host}/api/auth/registrationUser`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/registrationSellerPerson`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/confirmEmailUser`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/confirmEmailSellerPerson`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/resetPasswordUser`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/resetPasswordSellerPerson`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/checkServerTokenUserResetPassword`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/checkServerTokenSellerPersonResetPassword`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/newPasswordUser`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/newPasswordSellerPerson`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/loginUser`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/loginSellerPerson`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/auth/checkShopNameRegistrationSellerPersonRequest`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/api/all`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/resources/all`)) {
      return next.handle(request);
    }

    if (request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
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
