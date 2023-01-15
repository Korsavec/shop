import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {NewPasswordUserComponent} from "./new-password-user/new-password-user.component";
import {ResetPasswordUserComponent} from "./reset-password-user/reset-password-user.component";
import {
  ResetPasswordSellerPersonComponent
} from "./reset-password-seller-person/reset-password-seller-person.component";
import {ResetComponent} from "./reset/reset.component";
import {NewPasswordSellerPersonComponent} from "./new-password-seller-person/new-password-seller-person.component";

const routes: Routes = [

  { path: '', component: ModResetPasswordComponent, children: [
      { path: '', component: ResetComponent, pathMatch: 'full' }
    ] },

  { path: 'user', component: ResetPasswordUserComponent, pathMatch: 'full' },
  { path: 'sellerPerson', component: ResetPasswordSellerPersonComponent, pathMatch: 'full' },

  { path: 'newPasswordUser/:token', component: NewPasswordUserComponent },
  { path: 'newPasswordSellerPerson/:token', component: NewPasswordSellerPersonComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModResetPasswordRoutingModule { }
