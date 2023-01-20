import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModLoginRoutingModule} from './mod-login-routing.module';
import {ModLoginComponent} from './mod-login.component';
import {ModMainModule} from "../mod-main/mod-main.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from './login/login.component';


@NgModule({
  declarations: [
    ModLoginComponent,
    LoginComponent,
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
