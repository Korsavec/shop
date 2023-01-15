import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidateService {


  constructor() { }


  patternEmail(): string {
    return '^(|(([A-Za-z0-9]{1,25}_)|([A-Za-z0-9]{1,25}-)|([A-Za-z0-9]{1,25}\\.))*[A-Za-z0-9]{1,25}@(([A-Za-z0-9]{1,25}\\.)|([A-Za-z0-9]{1,25}-))*[A-Za-z0-9]{2,25}\\.[a-zA-Z]{2,6})$';
  }

  patternPassword(): string {
    return '^[0-9a-zA-Z@#$]+$';
  }

  patternToken(token:string): boolean {
    const pattern = /^([a-z0-9]{8})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{12})$/g;
    return pattern.test(token);

  }

  shopName(token:string): boolean {
    const pattern = /^[0-9а-яА-Я]+$/g;
    return pattern.test(token);

  }



  patternOnlyLettersNumbersHyphenSlash(): string {
    return '^[0-9а-яА-Я-\s]+$';
  }

  patternOnlyLettersNumbersHyphen(): string {
    return '^[0-9а-яА-Я/]+$';
  }

  patternOnlyLettersAndNumbers(): string {
    return '^[0-9а-яА-Я]+$';
  }

  patternOnlyNumbersRegExp(): string {
    return '^[0-9]+$';
  }

  patternOnlyLetters(): string {
    return '^[а-яА-Я]+$';
  }

  patternRegion(): string {
    return '^[Сахалин]+$';
  }

  patternCity(): string {
    return '^[А-Яа-я-\s]+$';
  }





}
