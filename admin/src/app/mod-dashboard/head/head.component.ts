import {Component} from '@angular/core';
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent {

  constructor(private generalService: GeneralService) {
  }


  logOut() {

    this.generalService.removeStorageItem('auth-admin');

  }


}
