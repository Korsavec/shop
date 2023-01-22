import {Component, OnInit} from '@angular/core';
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  message:string = '';

  constructor(private httpRequestService: HttpRequestService, private localStorageService: LocalStorageService, private router: Router) { }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.httpRequestService.adminTestGetText().subscribe({
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
