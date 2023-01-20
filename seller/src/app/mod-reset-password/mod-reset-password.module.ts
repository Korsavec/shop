import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModResetPasswordRoutingModule} from './mod-reset-password-routing.module';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {NewPasswordComponent} from './new-password/new-password.component';
import {ResetComponent} from './reset/reset.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ModMainModule} from "../mod-main/mod-main.module";


@NgModule({
  declarations: [
    ModResetPasswordComponent,
    NewPasswordComponent,
    ResetComponent
  ],
  imports: [
    CommonModule,
    ModResetPasswordRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule,
    ReactiveFormsModule,
    ModMainModule
  ]
})
export class ModResetPasswordModule { }
