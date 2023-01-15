import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModUserComponent} from './mod-user.component';
import {UserComponent} from "./user/user.component";

const routes: Routes = [

  { path: '', component: ModUserComponent, children: [
      { path: '', component: UserComponent, pathMatch: 'full' }
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModUserRoutingModule { }
