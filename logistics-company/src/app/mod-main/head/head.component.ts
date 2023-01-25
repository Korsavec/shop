import {Component, OnInit} from '@angular/core';
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowLogisticsCompanyAccount: boolean = false;

  constructor(private generalService: GeneralService) {
  }

  ngOnInit(): void {

    this.isShowLogisticsCompanyAccount = this.generalService.isTokenExpired('auth-logistics-company')
    this.closeLogin = !this.isShowLogisticsCompanyAccount;

  }


  logOut() {

    this.closeLogin = true;
    this.isShowLogisticsCompanyAccount = false;

    this.generalService.removeStorageItem('auth-logistics-company');
  }

}
