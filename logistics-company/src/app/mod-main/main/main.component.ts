import {Component, OnInit} from '@angular/core';
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  message:string = '';


  constructor(private httpRequestService: HttpRequestService, private localStorageService: LocalStorageService, private router: Router) { }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/dashboard']).then(() => {});

    }

  }

}
