import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {StoreSellerPersonService} from "../../_service/seller/person/store-seller-person.service";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  message:string = '';

  showAdd:boolean = false;

  constructor(private httpClientService: HttpClientService, private storeSellerPersonService: StoreSellerPersonService, private router: Router) { }

  ngOnInit(): void {

    if (this.storeSellerPersonService.isTokenExpired()) {

      this.httpClientService.sellerPersonTestGetText().subscribe({
        next: data => {

          let responseData:any = data;
          this.message = responseData.PERSON

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
