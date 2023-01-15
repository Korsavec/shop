import {Component, OnInit} from '@angular/core';
import {GeneralStoreService} from "../../_service/general/general-store.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowUserAccount: boolean = false;
  isShowSellerAccount: boolean = false;
  isShowUserAndSellerAccount: boolean = false;

  constructor(private generalStoreService: GeneralStoreService) {
  }

  ngOnInit(): void {

    this.isShowUserAccount = this.generalStoreService.isTokenExpired('auth-user')
    this.isShowSellerAccount = this.generalStoreService.isTokenExpired('auth-seller-person')

    if (this.isShowUserAccount || this.isShowSellerAccount) {
      this.closeLogin = false;
    }

    if (this.isShowUserAccount && this.isShowSellerAccount) {
      this.isShowUserAccount = false;
      this.isShowSellerAccount = false;
      this.isShowUserAndSellerAccount = true;
    } else if (this.isShowUserAccount && !this.isShowSellerAccount) {
      this.isShowUserAccount = true;
      this.isShowSellerAccount = false;
      this.isShowUserAndSellerAccount = false;
    } else if (!this.isShowUserAccount && this.isShowSellerAccount) {
      this.isShowUserAccount = false;
      this.isShowSellerAccount = true;
      this.isShowUserAndSellerAccount = false;
    }


  }

  logOut() {

    this.closeLogin = true;
    this.isShowUserAccount = false;
    this.isShowSellerAccount = false;
    this.isShowUserAndSellerAccount = false;

    this.generalStoreService.removeStoreItem('auth-user');
    this.generalStoreService.removeStoreItem('auth-seller-person');
  }


}
