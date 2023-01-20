import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModErrorRoutingModule} from './mod-error-routing.module';
import {ModErrorComponent} from './mod-error.component';
import {ModMainModule} from "../mod-main/mod-main.module";


@NgModule({
  declarations: [
    ModErrorComponent
  ],
  imports: [
    CommonModule,
    ModErrorRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule
  ]
})
export class ModErrorModule { }
