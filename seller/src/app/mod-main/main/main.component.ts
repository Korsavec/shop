import {Component, OnInit} from '@angular/core';
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {Router} from "@angular/router";
import {LocalStoreService} from "../../_service/store/local-store.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  message:string = '';


  constructor(private httpClientService: HttpClientService, private localStoreService: LocalStoreService, private router: Router) { }

  ngOnInit(): void {

    if (this.localStoreService.isTokenExpired()) {

      this.router.navigate(['/dashboard']).then(() => {});

    }

  }

}
