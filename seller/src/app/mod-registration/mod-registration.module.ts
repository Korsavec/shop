import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModRegistrationRoutingModule} from './mod-registration-routing.module';
import {ModRegistrationComponent} from './mod-registration.component';
import {ConfirmEmailComponent} from './confirm-email/confirm-email.component';
import {RegistrationComponent} from './registration/registration.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ModRegistrationComponent,
    ConfirmEmailComponent,
    RegistrationComponent
  ],
  imports: [
    CommonModule,
    ModRegistrationRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule,
    ReactiveFormsModule,
  ]
})
export class ModRegistrationModule { }
