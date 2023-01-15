import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModRegistrationUserRoutingModule} from './mod-registration-user-routing.module';
import {ModRegistrationUserComponent} from './mod-registration-user.component';
import {RegistrationUserComponent} from './registration-user/registration-user.component';
import {ConfirmEmailComponent} from './confirm-email/confirm-email.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ModRegistrationUserComponent,
    RegistrationUserComponent,
    ConfirmEmailComponent
  ],
  imports: [
    CommonModule,
    ModRegistrationUserRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
        "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule,
    ReactiveFormsModule,
  ]
})
export class ModRegistrationUserModule { }
