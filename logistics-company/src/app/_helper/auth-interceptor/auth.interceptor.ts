import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {properties} from "../../../resources/application.properties";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private host: string = properties.apiUrl;

  constructor(private localStorageService: LocalStorageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (request.url.includes(`${this.host}/api/auth/registrationLogisticsCompany`)
      || request.url.includes(`${this.host}/api/auth/confirmEmailLogisticsCompany`)
      || request.url.includes(`${this.host}/api/auth/resetPasswordLogisticsCompany`)
      || request.url.includes(`${this.host}/api/auth/checkTokenLogisticsCompanyResetPassword`)
      || request.url.includes(`${this.host}/api/auth/newPasswordLogisticsCompany`)
      || request.url.includes(`${this.host}/api/auth/loginLogisticsCompany`)
      || request.url.includes(`${this.host}/api/all`)
      || request.url.includes(`${this.host}/resources/all`)
      || request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
      return next.handle(request);
    }



    // Для пользователя
    if (request.url.includes(`${this.host}/api/AccountGuard/logisticsCompany`)) {
      const token = this.localStorageService.getStorageItem('auth-logistics-company');

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
