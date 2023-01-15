import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModSellerPersonComponent} from './mod-seller-person.component';
import {PersonComponent} from "./person/person.component";

const routes: Routes = [

  { path: '', component: ModSellerPersonComponent, children: [
      { path: '', component: PersonComponent }
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModSellerPersonRoutingModule { }
