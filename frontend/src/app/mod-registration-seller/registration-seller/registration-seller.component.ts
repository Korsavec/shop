import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {Router} from "@angular/router";
import {StoreSellerPersonService} from "../../_service/seller/person/store-seller-person.service";

@Component({
  selector: 'app-registration-seller',
  templateUrl: './registration-seller.component.html',
  styleUrls: ['./registration-seller.component.css']
})
export class RegistrationSellerComponent implements OnInit {

  public submitForm: boolean = true;

  public responseServer: boolean = true;

  public responseView: boolean = true;

  public emailExists: boolean = false;


  public editMail!:boolean;
  public editPass!:boolean;

  passportFile: any;

// Изображение выбрано и оно корректно true
  imageOk = false;


  showShopNameChecked = false
  shopNameCheckedTrue = false
  shopNameOkTrue = false


  disabledButton = true



  loginForm:FormGroup = this.formBuilder.group(
    {
      p_phone: ['']
      , p_email1: ['']
      , p_email2: ['']
      , p_password1: ['']
      , p_password2: ['']
      , p_surname: [''], p_name: ['']
      , p_middle_name: ['']
      , p_date_birth: ['']
      , p_number_passport: ['']
      , p_inn: ['']
      , p_shop_name: ['']
      , p_img_passport: ['']
      , p_region: ['']
      , p_city: ['']
      , p_street: ['']
      , p_house: ['']
      , p_building: ['']
      , p_apartment: ['']
      , p_bank_account: ['']
      , p_beak_bank: ['']
      , p_bank_name: ['']
      , p_correspondent_account: ['']
      , inn_bank: ['']
      , kpp_bank: ['']
    });


  constructor(private validateService: ValidateService, private formBuilder: FormBuilder,
              private httpClientService: HttpClientService, private router: Router,
              private storeSellerPersonService: StoreSellerPersonService) {
  }

  ngOnInit(): void {

    if (this.storeSellerPersonService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      this.loginForm = this.formBuilder.group({
        p_phone: ['', {
          validators: [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(10),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp())
          ],
          updateOn: 'change'
        }],
        p_email1: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_email2: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_password1: ['', {
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
        p_password2: ['', {
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
        p_surname: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(45),
            Validators.pattern(this.validateService.patternOnlyLetters()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_name: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(45),
            Validators.pattern(this.validateService.patternOnlyLetters()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_middle_name: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(45),
            Validators.pattern(this.validateService.patternOnlyLetters()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_date_birth: ['', {
          validators: [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(10)
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_number_passport: ['', {
          validators: [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(10),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_inn: ['', {
          validators: [
            Validators.required,
            Validators.minLength(12),
            Validators.maxLength(12),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_shop_name: ['', {
          validators: [
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(30),
            Validators.pattern(this.validateService.patternOnlyLettersAndNumbers()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_img_passport: ['', {
          validators: [
            Validators.required
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_region: ['Сахалин', {
          validators: [
            Validators.required,
            Validators.minLength(7),
            Validators.maxLength(7),
            Validators.pattern(this.validateService.patternRegion()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_city: ['', {
          validators: [
            Validators.required,
            Validators.minLength(2),
            Validators.maxLength(25),
            Validators.pattern(this.validateService.patternCity()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_street: ['', {
          validators: [
            Validators.required,
            Validators.minLength(2),
            Validators.maxLength(50),
            Validators.pattern(this.validateService.patternOnlyLettersNumbersHyphenSlash()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_house: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(11),
            Validators.pattern(this.validateService.patternOnlyLettersNumbersHyphen()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_building: ['', {
          validators: [
            Validators.minLength(1),
            Validators.maxLength(11),
            Validators.pattern(this.validateService.patternOnlyLettersNumbersHyphen()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_apartment: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(4),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_bank_account: ['', {
          validators: [
            Validators.required,
            Validators.minLength(20),
            Validators.maxLength(20),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_beak_bank: ['', {
          validators: [
            Validators.required,
            Validators.minLength(9),
            Validators.maxLength(9),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_bank_name: ['', {
          validators: [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(50),
            Validators.pattern(this.validateService.patternOnlyLettersNumbersHyphenSlash()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_correspondent_account: ['', {
          validators: [
            Validators.required,
            Validators.minLength(20),
            Validators.maxLength(20),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_inn_bank: ['', {
          validators: [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(10),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        p_kpp_bank: ['', {
          validators: [
            Validators.required,
            Validators.minLength(9),
            Validators.maxLength(9),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }]
      }, {validators: this.matchPassword('p_email1', 'p_email2', 'p_password1', 'p_password2', 'p_shop_name')});
    }

  }

  get p_phone() {
    return this.loginForm.controls['p_phone'];
  }

  get p_email1() {
    return this.loginForm.controls['p_email1'];
  }

  get p_email2() {
    return this.loginForm.controls['p_email2'];
  }

  get p_password1() {
    return this.loginForm.controls['p_password1'];
  }

  get p_password2() {
    return this.loginForm.controls['p_password2'];
  }

  get p_surname() {
    return this.loginForm.controls['p_surname'];
  }

  get p_name() {
    return this.loginForm.controls['p_name'];
  }

  get p_middle_name() {
    return this.loginForm.controls['p_middle_name'];
  }

  get p_date_birth() {
    return this.loginForm.controls['p_date_birth'];
  }

  get p_number_passport() {
    return this.loginForm.controls['p_number_passport'];
  }

  get p_inn() {
    return this.loginForm.controls['p_inn'];
  }

  get p_shop_name() {
    return this.loginForm.controls['p_shop_name'];
  }

  get p_img_passport() {
    return this.loginForm.controls['p_img_passport'];
  }

  get p_region() {
    return this.loginForm.controls['p_region'];
  }

  get p_city() {
    return this.loginForm.controls['p_city'];
  }

  get p_street() {
    return this.loginForm.controls['p_street'];
  }

  get p_house() {
    return this.loginForm.controls['p_house'];
  }

  get p_building() {
    return this.loginForm.controls['p_building'];
  }

  get p_apartment() {
    return this.loginForm.controls['p_apartment'];
  }

  get p_bank_account() {
    return this.loginForm.controls['p_bank_account'];
  }

  get p_beak_bank() {
    return this.loginForm.controls['p_beak_bank'];
  }

  get p_bank_name() {
    return this.loginForm.controls['p_bank_name'];
  }

  get p_correspondent_account() {
    return this.loginForm.controls['p_correspondent_account'];
  }

  get p_inn_bank() {
    return this.loginForm.controls['p_inn_bank'];
  }

  get p_kpp_bank() {
    return this.loginForm.controls['p_kpp_bank'];
  }

  // Сверяем два пароля на одинаковость.
  // Сверяем два пароля на одинаковость.
  public matchPassword(p_email1: any, p_email2: any, p_password1: any, p_password2: any, p_shop_name: any): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {

      let shopNameLength = control.get(p_shop_name)?.value;

      this.disabledButton = !(shopNameLength.length > 2 && this.validateService.shopName(shopNameLength));


      if (this.editMail) {
        let mailOne = control.get(p_email1)?.value;
        let mailTwo = control.get(p_email2)?.value;

        if (mailOne != mailTwo && (mailOne.length >= 8 && mailTwo.length >= 8)) {
          return { 'noMatchEmail': true }
        }
      }

      else if (this.editPass) {
        let passwordOne = control.get(p_password1)?.value;
        let passwordTwo = control.get(p_password2)?.value;

        if (passwordOne != passwordTwo && (passwordOne.length >= 6 && passwordTwo.length >= 6)) {
          return { 'noMatchPassword': true }
        }
      }

      return null;

    }
  }



  submit() {
    const person: any = {
      phone: null,
      email: null,
      password: null,
      surname: null,
      name: null,
      middleName: null,
      dateBirth: null,
      numberPassport: null,
      inn: null,
      shopName: null,
      region: null,
      city: null,
      street: null,
      building: null,
      house: null,
      apartment: null,
      bankAccount: null,
      beakBank: null,
      bankName: null,
      correspondentAccount: null,
      innBank: null,
      kppBank: null
    }

    person.phone = this.p_phone.value;
    person.email = this.p_email1.value;
    person.password = this.p_password1.value;
    person.surname = this.p_surname.value;
    person.name = this.p_name.value;
    person.middleName = this.p_middle_name.value;
    person.dateBirth = this.p_date_birth.value;
    person.numberPassport = this.p_number_passport.value;
    person.inn = this.p_inn.value;
    person.shopName = this.p_shop_name.value;
    person.region = this.p_region.value;
    person.city = this.p_city.value;
    person.street = this.p_street.value;
    person.building = this.p_building.value;
    person.house = this.p_house.value;
    person.apartment = this.p_apartment.value;
    person.bankAccount = this.p_bank_account.value;
    person.beakBank = this.p_beak_bank.value;
    person.bankName = this.p_bank_name.value;
    person.correspondentAccount = this.p_correspondent_account.value;
    person.innBank = this.p_inn_bank.value;
    person.kppBank = this.p_kpp_bank.value;

    this.submitForm = false;

    const formData = new FormData()
    formData.append('image', this.passportFile)
    formData.append('registrationSellerPerson', JSON.stringify(person))
    if (this.imageOk) {
      this.httpClientService.registrationSellerPerson(formData).subscribe({
        next: () => {
          this.responseServer = false;

        },
        error:(err) => {
          if (err.error.status == 400
            && err.error.message == 'SellerPerson exist') {
            this.emailExists = true;
            this.submitForm = true;
            this.responseServer  = true;

          } else {
            this.responseServer = false;
            this.responseView = false;
          }

        }
      });
    }
  }

  changeFieldMail() {
    this.emailExists = false
    this.editMail = true;
    this.editPass = false;
  }


  changeFieldPass() {
    this.editMail = false;
    this.editPass = true;
  }

  pickedImage(imageInput: any) {
    if (imageInput.target.files[0]) {
      this.passportFile = imageInput.target.files[0]

      if (this.passportFile.type.toString() !== 'image/jpeg' && this.passportFile.type.toString() !== 'image/png') {
        this.p_img_passport.setErrors({'imageErrorNoCorrect': true});
        this.imageOk = false;
        this.passportFile = null;

      }

      else if (this.passportFile.size > 10485760) {
        this.p_img_passport.setErrors({'imageErrorMB': true});
        this.imageOk = false;
        this.passportFile = null;
      }

      else if (this.passportFile.size < 20480) {
        this.p_img_passport.setErrors({'imageErrorByte': true});
        this.imageOk = false;
        this.passportFile = null;
      }

      else {
        this.imageOk = true;
      }

    }

  }

  checkShopName(shopName: string) {

    if (this.p_shop_name.status !== 'INVALID') {
      // В этот блок идём если всё хорошо

      this.showShopNameChecked = true;
      this.shopNameCheckedTrue = true;

      this.disabledButton = true

      setTimeout(() => {

        this.httpClientService.checkShopNameRegistrationSellerPersonRequest(shopName).subscribe({
          next: (value:any) => {

            this.disabledButton = false

            if (value.body.message == 'no') {
              this.shopNameCheckedTrue = false;
              this.shopNameOkTrue = true;
            } else {
              this.shopNameCheckedTrue = false;
              this.shopNameOkTrue = false;
            }

          },
          error: () => {
            this.shopNameCheckedTrue = false;
            this.shopNameOkTrue = false;
            this.disabledButton = false
          }
        })

      }, 3000)

    }


  }

}
