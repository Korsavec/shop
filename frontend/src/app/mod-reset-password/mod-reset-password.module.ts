import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModResetPasswordRoutingModule} from './mod-reset-password-routing.module';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ResetComponent} from './reset/reset.component';
import {NewPasswordComponent} from './new-password/new-password.component';


@NgModule({
  declarations: [
    ModResetPasswordComponent,
    ResetComponent,
    NewPasswordComponent,
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
