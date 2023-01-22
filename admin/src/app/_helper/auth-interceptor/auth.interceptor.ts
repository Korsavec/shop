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

    if (request.url.includes(`${this.host}/api/auth/confirmEmailAdmin`) //+
      || request.url.includes(`${this.host}/api/auth/resetPasswordAdmin`) //+
      || request.url.includes(`${this.host}/api/auth/checkTokenAdminResetPassword`) //+
      || request.url.includes(`${this.host}/api/auth/newPasswordAdmin`) //+
      || request.url.includes(`${this.host}/api/auth/loginAdmin`) //+
      || request.url.includes(`${this.host}/api/all`) //+
      || request.url.includes(`${this.host}/resources/all`) //+
      || request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
      return next.handle(request);
    }



    // Для auth-admin
    if (request.url.includes(`${this.host}/api/AccountGuard/admin`)) {
      const token = this.localStorageService.getStorageItem('auth-admin');

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
