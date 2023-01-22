import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomPreloadStrategy} from "./_custom-type/preloading/custom-preload-strategy";
import {CanLoadGuard} from "./_custom-type/can-load/can-load.guard";

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
    path: 'dashboard', loadChildren: () => import('./mod-dashboard/mod-dashboard.module').then(m => m.ModDashboardModule),

    // Передаётся в CustomPreloadStrategy - Этот класс загружает модуль лениво или предварительно.
    data: {preload: true},

    // Родительский модуль user будет загружен лениво, а все дочерние модули,
    // в зависимости от указанного data:{} параметра в дочернем роутере
    canMatch: [CanLoadGuard] },


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
