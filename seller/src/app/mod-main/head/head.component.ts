import {Component, OnInit} from '@angular/core';
import {GeneralStoreService} from "../../_service/general/general-store.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowSellerAccount: boolean = false;

  constructor(private generalStoreService: GeneralStoreService) {
  }

  ngOnInit(): void {

    this.isShowSellerAccount = this.generalStoreService.isTokenExpired('auth-seller')
    this.closeLogin = !this.isShowSellerAccount;

  }


  logOut() {

    this.closeLogin = true;
    this.isShowSellerAccount = false;

    this.generalStoreService.removeStoreItem('auth-seller');
  }

}
