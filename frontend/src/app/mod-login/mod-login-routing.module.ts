import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModLoginComponent} from './mod-login.component';
import {LoginSellerPersonComponent} from "./login-seller-person/login-seller-person.component";
import {LoginComponent} from "./login/login.component";
import {LoginUserComponent} from "./login-user/login-user.component";

const routes: Routes = [

  { path: '', component: ModLoginComponent, children: [
      { path: '', component: LoginComponent, pathMatch: 'full' }
    ] },

  { path: 'user', component: LoginUserComponent, pathMatch: 'full' },

  { path: 'sellerPerson', component: LoginSellerPersonComponent, pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModLoginRoutingModule { }
