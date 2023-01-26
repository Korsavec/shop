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

  public registration(logisticsPerson: any): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationLogisticsPerson`, logisticsPerson,
      {observe: 'response'});
  }

  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailLogisticsPerson`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(logisticsPersonEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordLogisticsPerson`, logisticsPersonEmail,
      {observe: 'response'});
  }

  public checkTokenLogisticsPersonResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkTokenLogisticsPersonResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public newPassword(logisticsPersonPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordLogisticsPerson`, logisticsPersonPassword,
      {observe: 'response'});
  }

  public login(logisticsPerson: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginLogisticsPerson`, logisticsPerson,
      {observe: 'response'});
  }


  /*--------------------*/

  /* Тестовый */
  public logisticsPersonTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/logisticsPerson`);
  }

}
