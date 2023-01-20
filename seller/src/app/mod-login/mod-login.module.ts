import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModLoginRoutingModule} from './mod-login-routing.module';
import {ModLoginComponent} from './mod-login.component';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ModMainModule} from "../mod-main/mod-main.module";


@NgModule({
  declarations: [
    ModLoginComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    ModLoginRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule,

    FormsModule,
    ReactiveFormsModule
  ]
})
export class ModLoginModule { }
