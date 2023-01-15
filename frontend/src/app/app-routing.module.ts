import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomPreloadStrategy} from "./custom-type/preloading/custom-preload-strategy";
import {CanLoadGuard} from "./custom-type/can-load/can-load.guard";

const routes: Routes = [

  { path: '', loadChildren: () => import('./mod-main/mod-main.module').then(m => m.ModMainModule),
    data: {preload: true}, canMatch: [CanLoadGuard]},

  { path: 'registrationUser', loadChildren: () => import('./mod-registration-user/mod-registration-user.module').then(m => m.ModRegistrationUserModule),
    data: {preload: true}, canMatch: [CanLoadGuard]},

  { path: 'registrationSeller', loadChildren: () => import('./mod-registration-seller/mod-registration-seller.module').then(m => m.ModRegistrationSellerModule),
    data: {preload: true}, canMatch: [CanLoadGuard]},

  { path: 'login', loadChildren: () => import('./mod-login/mod-login.module').then(m => m.ModLoginModule),
    data: {preload: true}, canMatch: [CanLoadGuard]},

  { path: 'user', loadChildren: () => import('./mod-user/mod-user.module').then(m => m.ModUserModule),

    // Передаётся в CustomPreloadStrategy - Этот класс загружает модуль лениво или предварительно.
    data: {preload: true},

    // Родительский модуль user будет загружен лениво, а все дочерние модули,
    // в зависимости от указанного data:{} параметра в дочернем роутере
    canMatch: [CanLoadGuard] },

  { path: 'sellerPerson', loadChildren: () => import('./mod-seller-person/mod-seller-person.module').then(m => m.ModSellerPersonModule),
    data: {preload: true}, canMatch: [CanLoadGuard]},

  { path: 'resetPassword', loadChildren: () => import('./mod-reset-password/mod-reset-password.module').then(m => m.ModResetPasswordModule) },


  { path: '**', loadChildren: () => import('./mod-error/mod-error.module').then(m => m.ModErrorModule),
    data: {preload: true}, canMatch: [CanLoadGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes,

    {preloadingStrategy: CustomPreloadStrategy}

  )],

  exports: [RouterModule]

})
export class AppRoutingModule { }
