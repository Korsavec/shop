import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModRegistrationSellerRoutingModule} from './mod-registration-seller-routing.module';
import {ModRegistrationSellerComponent} from './mod-registration-seller.component';
import {RegistrationSellerComponent} from './registration-seller/registration-seller.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ConfirmEmailSellerPersonComponent} from './confirm-email-seller-person/confirm-email-seller-person.component';


@NgModule({
  declarations: [
    ModRegistrationSellerComponent,
    RegistrationSellerComponent,
    ConfirmEmailSellerPersonComponent
  ],
    imports: [
        CommonModule,
        ModRegistrationSellerRoutingModule,

        /* Импортировали "ModMainModule" модуль для получения
        "HeadComponent" для вставки в шапку страницы 404 */
        ModMainModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class ModRegistrationSellerModule { }
