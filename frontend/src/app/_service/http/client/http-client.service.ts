import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {properties} from "../../../../resources/application.properties";

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private host: string = properties.apiUrl;

  constructor(private http: HttpClient) { }

  /* Регистрация, аутентификация, сброс пароля */

  public registrationUser(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationUser`, user,
      {observe: 'response'});
  }

  public registrationSellerPerson(user: any): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationSellerPerson`, user,
      {observe: 'response'});
  }

  public confirmEmailUser(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailUser`,
      {"token": token}, {observe: 'response'});
  }

  public confirmEmailSellerPerson(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailSellerPerson`,
      {"token": token}, {observe: 'response'});
  }

  public resetPasswordUser(userEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordUser`, userEmail,
      {observe: 'response'});
  }

  public resetPasswordSellerPerson(sellerPersonEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordSellerPerson`, sellerPersonEmail,
      {observe: 'response'});
  }

  public checkServerTokenUserResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkServerTokenUserResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public checkServerTokenSellerPersonResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/checkServerTokenSellerPersonResetPassword`,
      {"token": token}, {observe: 'response'});
  }

  public newPasswordUser(userPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordUser`, userPassword,
      {observe: 'response'});
  }

  public newPasswordSellerPerson(userPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordSellerPerson`, userPassword,
      {observe: 'response'});
  }

  public loginUser(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginUser`, user,
      {observe: 'response'});
  }

  public checkShopNameRegistrationSellerPersonRequest(shopName: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/checkShopNameRegistrationSellerPersonRequest`,
      {"shopName": shopName}, {observe: 'response'});
  }


  public loginSellerPerson(sellerPerson: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    console.log('111111111111111')
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginSellerPerson`, sellerPerson,
      {observe: 'response'});
  }


  /*--------------------*/

  /* Тестовый */
  public userTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/user`);
  }

  /* Тестовый */
  public sellerPersonTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/seller`);
  }

}
