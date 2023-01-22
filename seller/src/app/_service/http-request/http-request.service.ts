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

  public registration(seller: any): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationSeller`, seller,
      {observe: 'response'});
  }

  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailSeller`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(sellerEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordSeller`, sellerEmail,
      {observe: 'response'});
  }

  public checkTokenSellerResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkTokenSellerResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public newPassword(sellerPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordSeller`, sellerPassword,
      {observe: 'response'});
  }

  public login(seller: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginSeller`, seller,
      {observe: 'response'});
  }

  public checkShopName(shopName: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/checkShopName`,
      {"shopName": shopName}, {observe: 'response'});
  }


  /*--------------------*/

  /* Тестовый */
  public sellerTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/seller`);
  }


}
