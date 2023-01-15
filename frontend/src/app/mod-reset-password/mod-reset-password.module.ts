import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModResetPasswordRoutingModule} from './mod-reset-password-routing.module';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ResetPasswordUserComponent} from './reset-password-user/reset-password-user.component';
import {NewPasswordUserComponent} from './new-password-user/new-password-user.component';
import {
  ResetPasswordSellerPersonComponent
} from './reset-password-seller-person/reset-password-seller-person.component';
import {NewPasswordSellerPersonComponent} from './new-password-seller-person/new-password-seller-person.component';
import {ResetComponent} from './reset/reset.component';


@NgModule({
  declarations: [
    ModResetPasswordComponent,
    ResetPasswordUserComponent,
    NewPasswordUserComponent,
    ResetPasswordSellerPersonComponent,
    NewPasswordSellerPersonComponent,
    ResetComponent
  ],
  imports: [
    CommonModule,
    ModResetPasswordRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule,
    ReactiveFormsModule,
  ]
})
export class ModResetPasswordModule { }
