export class User {

  private _email: string;
  private _iat: string;
  private _exp: string;
  private _roles: [];


  constructor(email: string, iat: string, exp: string, roles: []) {
    this._email = email;
    this._iat = iat;
    this._exp = exp;
    this._roles = roles;
  }


  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get iat(): string {
    return this._iat;
  }

  set iat(value: string) {
    this._iat = value;
  }

  get exp(): string {
    return this._exp;
  }

  set exp(value: string) {
    this._exp = value;
  }

  get roles(): [] {
    return this._roles;
  }

  set roles(value: []) {
    this._roles = value;
  }
}



