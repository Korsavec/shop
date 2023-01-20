import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CanLoadGuard} from "./custom-type/can-load/can-load.guard";
import {CustomPreloadStrategy} from "./custom-type/preloading/custom-preload-strategy";

const routes: Routes = [

  {
    path: '', loadChildren: () => import('./mod-main/mod-main.module').then(m => m.ModMainModule),
    data: {preload: true}, canMatch: [CanLoadGuard]
  },

  {
    path: 'registration', loadChildren: () => import('./mod-registration/mod-registration.module').then(m => m.ModRegistrationModule),
    data: {preload: true}, canMatch: [CanLoadGuard]
  },

  {
    path: 'login', loadChildren: () => import('./mod-login/mod-login.module').then(m => m.ModLoginModule),
    data: {preload: true}, canMatch: [CanLoadGuard]
  },

  {
    path: 'resetPassword', loadChildren: () => import('./mod-reset-password/mod-reset-password.module').then(m => m.ModResetPasswordModule),
    data: {preload: true}, canMatch: [CanLoadGuard]
  },

  {
    path: '**', loadChildren: () => import('./mod-error/mod-error.module').then(m => m.ModErrorModule),
    data: {preload: true}, canMatch: [CanLoadGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,

    {preloadingStrategy: CustomPreloadStrategy}

  )],

  exports: [RouterModule]

})
export class AppRoutingModule { }
