import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {StoreUserService} from "../../_service/user/store-user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  message:string = '';

  showAdd:boolean = false;

  constructor(private httpClientService: HttpClientService, private storeUserService: StoreUserService, private router: Router) { }

  ngOnInit(): void {

    if (this.storeUserService.isTokenExpired()) {

      this.httpClientService.userTestGetText().subscribe({
        next: data => {

          let responseData:any = data;
          this.message = responseData.USER

          // this.message = responseData.message

        }
      })


    } else {
      this.router.navigate(['/']).then(() => {});
    }

  }

  addProduct() {

    this.showAdd = true;
  }

}
