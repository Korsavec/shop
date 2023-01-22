import {Component, OnInit} from '@angular/core';
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowSellerAccount: boolean = false;

  constructor(private generalService: GeneralService) {
  }

  ngOnInit(): void {

    this.isShowSellerAccount = this.generalService.isTokenExpired('auth-seller')
    this.closeLogin = !this.isShowSellerAccount;

  }


  logOut() {

    this.closeLogin = true;
    this.isShowSellerAccount = false;

    this.generalService.removeStorageItem('auth-seller');
  }

}
