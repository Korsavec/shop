import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {ValidateService} from "../../_service/validation/validate.service";
import {StoreSellerPersonService} from "../../_service/seller/person/store-seller-person.service";
import {GeneralStoreService} from "../../_service/general/general-store.service";

@Component({
  selector: 'app-login-seller-person',
  templateUrl: './login-seller-person.component.html',
  styleUrls: ['./login-seller-person.component.css']
})
export class LoginSellerPersonComponent implements OnInit {

  loginForm:FormGroup = this.formBuilder.group({email: [''],password: [''], checkbox: ['']});

  errorMessage!: string;

  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpClientService: HttpClientService, private storeSellerPersonService: StoreSellerPersonService,
              private generalStoreService: GeneralStoreService) {
  }

  ngOnInit(): void {

    if (this.storeSellerPersonService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      this.loginForm = this.formBuilder.group({
        email: ['', {
          validators: [
            Validators.required,
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
        }],
        password: ['', {
          validators: [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(24),
            Validators.pattern(this.validateService.patternPassword()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        checkbox: [false],

      });

    }



  }

  get email() {
    return this.loginForm.controls['email'];
  }

  get password() {
    return this.loginForm.controls['password'];
  }

  get checkbox() {
    return this.loginForm.controls['checkbox'];
  }


  submit() {


    // Проверяем на блокировку пользователя при многократной ошибке входа
    if (this.storeSellerPersonService.getStoreItem('counterLogin') == '10') {
      this.errorMessage = 'Что-то пошло не так. Попробуйте позе';
      this.generalStoreService.isBlockedLogin();
    } else {
      this.httpClientService.loginSellerPerson(this.loginForm.value).subscribe({
        next: data => {

          let responseData:any;

          responseData = data;

          this.storeSellerPersonService.saveJwt(responseData.body.message);
          this.storeSellerPersonService.removeCount()
          this.gotoMessage();
        },
        error: err => {
          this.generalStoreService.isBlockedLogin();
          this.errorMessage = err.error.message;
        }
      });
    }



  }

  deleteErrorMessage() {
    this.errorMessage = ''
  }

  gotoMessage() {
    this.router.navigate(['/']).then(() => {});
  }

}
