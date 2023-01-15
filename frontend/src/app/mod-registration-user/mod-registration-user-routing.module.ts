import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModRegistrationUserComponent} from './mod-registration-user.component';
import {ConfirmEmailComponent} from "./confirm-email/confirm-email.component";
import {RegistrationUserComponent} from "./registration-user/registration-user.component";

const routes: Routes = [

  { path: '', component: ModRegistrationUserComponent, children: [
      { path: '', component: RegistrationUserComponent }
    ] },

  { path: 'confirmEmailUser/:token', component: ConfirmEmailComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModRegistrationUserRoutingModule { }
