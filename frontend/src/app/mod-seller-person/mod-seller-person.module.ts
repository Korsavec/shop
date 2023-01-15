import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModSellerPersonRoutingModule} from './mod-seller-person-routing.module';
import {ModSellerPersonComponent} from './mod-seller-person.component';
import {PersonComponent} from './person/person.component';
import {ModMainModule} from "../mod-main/mod-main.module";


@NgModule({
  declarations: [
    ModSellerPersonComponent,
    PersonComponent
  ],
  imports: [
    CommonModule,
    ModSellerPersonRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule
  ]
})
export class ModSellerPersonModule { }
