import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModRegistrationSellerComponent} from './mod-registration-seller.component';
import {RegistrationSellerComponent} from "./registration-seller/registration-seller.component";
import {ConfirmEmailSellerPersonComponent} from "./confirm-email-seller-person/confirm-email-seller-person.component";

const routes: Routes = [

  { path: '', component: ModRegistrationSellerComponent, children: [
      { path: '', component: RegistrationSellerComponent, pathMatch: 'full' }
    ] },

  { path: 'confirmEmailSellerPerson/:token', component: ConfirmEmailSellerPersonComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModRegistrationSellerRoutingModule { }
