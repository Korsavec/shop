import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {

  public submitForm: boolean = true;
  public responseServer: boolean = true;
  public responseView!: boolean;


  loginForm:FormGroup = this.formBuilder.group({email1: [''] ,email2: ['']});

  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpRequestService: HttpRequestService, private localStorageService: LocalStorageService) {
  }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      this.loginForm = this.formBuilder.group({
        email1: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
        }],
        email2: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
        }],

      }, {validators: this.matchEmail('email1', 'email2')});
    }

  }

  get email1() {
    return this.loginForm.controls['email1'];
  }

  get email2() {
    return this.loginForm.controls['email2'];
  }

  // Сверяем два email на одинаковость
  public matchEmail(email1: string, email2: string): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {

      let emailOne = control.get(email1)?.value;
      let emailTwo = control.get(email2)?.value;

      if (emailOne != emailTwo && (emailOne.length >= 8 && emailTwo.length >= 8)) {
        return { 'noMatch': true }
      }
      return null

    }
  }


  submit() {

    const logisticsPersonEmail: any = {
      email: null,
    }

    logisticsPersonEmail.email = this.email1.value;

    this.submitForm = false;


    this.httpRequestService.resetPassword(logisticsPersonEmail).subscribe({
      next: () => {
        this.responseServer = false;
        this.responseView = true;
      },
      error: () => {
        this.responseServer = false;
        this.responseView = false;
      }
    });
  }


}
