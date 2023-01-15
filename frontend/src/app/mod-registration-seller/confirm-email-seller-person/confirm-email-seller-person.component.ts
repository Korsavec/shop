import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {switchMap} from "rxjs";
import {ValidateService} from "../../_service/validation/validate.service";
import {StoreSellerPersonService} from "../../_service/seller/person/store-seller-person.service";

@Component({
  selector: 'app-confirm-email-seller-person',
  templateUrl: './confirm-email-seller-person.component.html',
  styleUrls: ['./confirm-email-seller-person.component.css']
})
export class ConfirmEmailSellerPersonComponent implements OnInit {

  private token: string = '';

  public responseServer: boolean = true;

  public responseConfirmMessage!: boolean;

  constructor(private validateService: ValidateService, private httpClientService: HttpClientService, private route: ActivatedRoute,
              private router: Router, private storeSellerPersonService: StoreSellerPersonService) { }


  ngOnInit() {

    if (this.storeSellerPersonService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      // Получаем токен из строки браузера
      this.route.paramMap.pipe(
        switchMap(params => params.getAll('token'))
      ).subscribe(data => this.token = data);


      if (this.validateService.patternToken(this.token)) {

        this.httpClientService.confirmEmailSellerPerson(this.token).subscribe({

          next: () => {

            this.responseServer = false;

            this.responseConfirmMessage = true;

          },

          error: () => {

            this.responseServer = false;

            this.responseConfirmMessage = false;

          }
        });

      } else {
        // fake
        setTimeout(() => this.fakeMessage(), 3000);
      }

    }




  }

  fakeMessage(){

    this.responseServer = false;

    this.responseConfirmMessage = true;
  }

}
