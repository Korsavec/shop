import {Component, OnInit} from '@angular/core';
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  closeLogin: boolean = true

  isShowUserAccount: boolean = false;

  constructor(private generalService: GeneralService) {
  }

  ngOnInit(): void {

    this.isShowUserAccount = this.generalService.isTokenExpired('auth-user')
    this.closeLogin = !this.isShowUserAccount;

  }

  logOut() {

    this.closeLogin = true;
    this.isShowUserAccount = false;

    this.generalService.removeStorageItem('auth-user');

  }


}
