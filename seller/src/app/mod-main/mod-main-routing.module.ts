import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModMainComponent} from './mod-main.component';
import {MainComponent} from "./main/main.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [

  { path: '', component: ModMainComponent, children:[
      { path: '', component: MainComponent, pathMatch: 'full' },
    ]  },

  { path: 'dashboard', component: DashboardComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModMainRoutingModule { }
