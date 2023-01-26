import {Component, OnInit} from '@angular/core';
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowLogisticsPersonAccount: boolean = false;

  constructor(private generalService: GeneralService) {
  }

  ngOnInit(): void {

    this.isShowLogisticsPersonAccount = this.generalService.isTokenExpired('auth-logistics-person')
    this.closeLogin = !this.isShowLogisticsPersonAccount;

  }


  logOut() {

    this.closeLogin = true;
    this.isShowLogisticsPersonAccount = false;

    this.generalService.removeStorageItem('auth-logistics-person');
  }

}
