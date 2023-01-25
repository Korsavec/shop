import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {properties} from "../../../resources/application.properties";

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  private host: string = properties.apiUrl;

  constructor(private http: HttpClient) { }

  /* Регистрация, аутентификация, сброс пароля */

  public registration(logisticsCompany: any): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationLogisticsCompany`, logisticsCompany,
      {observe: 'response'});
  }

  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailLogisticsCompany`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(logisticsCompanyEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordLogisticsCompany`, logisticsCompanyEmail,
      {observe: 'response'});
  }

  public checkTokenLogisticsCompanyResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkTokenLogisticsCompanyResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public newPassword(logisticsCompanyPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordLogisticsCompany`, logisticsCompanyPassword,
      {observe: 'response'});
  }

  public login(logisticsCompany: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginLogisticsCompany`, logisticsCompany,
      {observe: 'response'});
  }


  /*--------------------*/

  /* Тестовый */
  public logisticsCompanyTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/logisticsCompany`);
  }

}
