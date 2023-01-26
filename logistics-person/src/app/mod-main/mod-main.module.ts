import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModMainRoutingModule} from './mod-main-routing.module';
import {ModMainComponent} from './mod-main.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HeadComponent} from './head/head.component';
import {MainComponent} from './main/main.component';


@NgModule({
  declarations: [
    ModMainComponent,
    DashboardComponent,
    HeadComponent,
    MainComponent
  ],
  exports: [
    // Экспортировали компонент "шапка"
    HeadComponent
  ],
  imports: [
    CommonModule,
    ModMainRoutingModule
  ]
})
export class ModMainModule { }
