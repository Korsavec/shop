import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModUserRoutingModule} from './mod-user-routing.module';
import {ModUserComponent} from './mod-user.component';
import {UserComponent} from './user/user.component';
import {ModMainModule} from "../mod-main/mod-main.module";


@NgModule({
  declarations: [
    ModUserComponent,
    UserComponent
  ],
  imports: [
    CommonModule,
    ModUserRoutingModule,

    /* Импортировали "ModMainModule" модуль для получения
    "HeadComponent" для вставки в шапку страницы 404 */
    ModMainModule
  ]
})
export class ModUserModule { }
