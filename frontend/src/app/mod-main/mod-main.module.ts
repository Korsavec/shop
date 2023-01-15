import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModMainRoutingModule} from './mod-main-routing.module';
import {ModMainComponent} from './mod-main.component';
import {MainComponent} from './main/main.component';
import {HeadComponent} from './head/head.component';


@NgModule({
  declarations: [
    ModMainComponent,
    MainComponent,
    HeadComponent,
  ],
  exports: [
    // Экспортировали компонент "шапка" для вставки в страницу ошибки 404, Войти
    HeadComponent
  ],
  imports: [
    CommonModule,
    ModMainRoutingModule
  ]
})
export class ModMainModule { }
