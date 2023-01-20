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

  constructor(private generalStoreService: GeneralStoreService) {
  }

  ngOnInit(): void {

    this.isShowUserAccount = this.generalStoreService.isTokenExpired('auth-user')
    this.closeLogin = !this.isShowUserAccount;

  }

  logOut() {

    this.closeLogin = true;
    this.isShowUserAccount = false;

    this.generalStoreService.removeStoreItem('auth-user');

  }


}
