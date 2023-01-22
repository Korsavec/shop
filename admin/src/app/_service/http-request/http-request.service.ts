import {Injectable} from '@angular/core';
import {properties} from "../../../resources/application.properties";
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  private host: string = properties.apiUrl;

  constructor(private http: HttpClient) { }

  /* Регистрация, аутентификация, сброс пароля */


  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailAdmin`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(adminEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordAdmin`, adminEmail,
      {observe: 'response'});
  }

  public checkTokenAdminResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkTokenAdminResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public newPassword(adminPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordAdmin`, adminPassword,
      {observe: 'response'});
  }

  public login(admin: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginAdmin`, admin,
      {observe: 'response'});
  }


  /*--------------------*/

  /* Тестовый */
  public adminTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/admin`);
  }

}
