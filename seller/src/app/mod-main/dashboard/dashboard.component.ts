import {Component, OnInit} from '@angular/core';
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {Router} from "@angular/router";
import {LocalStoreService} from "../../_service/store/local-store.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  message:string = '';

  constructor(private httpClientService: HttpClientService, private localStoreService: LocalStoreService, private router: Router) { }

  ngOnInit(): void {

    if (this.localStoreService.isTokenExpired()) {

      this.httpClientService.sellerTestGetText().subscribe({
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


}
