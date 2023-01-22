import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {properties} from "../../../resources/application.properties";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private host: string = properties.apiUrl;

  constructor(private localStorageService: LocalStorageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (request.url.includes(`${this.host}/api/auth/registrationSeller`) //+
      || request.url.includes(`${this.host}/api/auth/confirmEmailSeller`) //+
      || request.url.includes(`${this.host}/api/auth/resetPasswordSeller`) //+
      || request.url.includes(`${this.host}/api/auth/checkTokenSellerResetPassword`) //+
      || request.url.includes(`${this.host}/api/auth/newPasswordSeller`) //+
      || request.url.includes(`${this.host}/api/auth/loginSeller`) //+
      || request.url.includes(`${this.host}/api/auth/checkShopName`) //+
      || request.url.includes(`${this.host}/api/all`) //+
      || request.url.includes(`${this.host}/resources/all`) //+
      || request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
      return next.handle(request);
    }



    // Для пользователя
    if (request.url.includes(`${this.host}/api/AccountGuard/seller`)) {
      const token = this.localStorageService.getStorageItem('auth-seller');

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
