import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModResetPasswordRoutingModule} from './mod-reset-password-routing.module';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {ResetComponent} from './reset/reset.component';
import {NewPasswordComponent} from './new-password/new-password.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ModResetPasswordComponent,
    ResetComponent,
    NewPasswordComponent
  ],
  imports: [
    CommonModule,
    ModResetPasswordRoutingModule,
    ReactiveFormsModule
  ]
})
export class ModResetPasswordModule { }
