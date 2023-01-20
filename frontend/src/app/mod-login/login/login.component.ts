import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {GeneralStoreService} from "../../_service/general/general-store.service";
import {LocalStoreService} from "../../_service/store/local-store.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm:FormGroup = this.formBuilder.group({email: [''],password: [''], checkbox: ['']});

  errorMessage!: string;

  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpClientService: HttpClientService, private localStoreService: LocalStoreService,
              private generalStoreService: GeneralStoreService) {
  }

  ngOnInit(): void {

    if (this.localStoreService.isTokenExpired()) {

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
    if (this.localStoreService.getStoreItem('counterLogin') == '10') {
      this.errorMessage = 'Что-то пошло не так. Попробуйте позе';
      this.generalStoreService.isBlockedLogin();
    } else {
      this.httpClientService.login(this.loginForm.value).subscribe({
        next: data => {

          let responseData:any;

          responseData = data;

          this.localStoreService.saveJwt(responseData.body.message);
          this.localStoreService.removeCount()
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
