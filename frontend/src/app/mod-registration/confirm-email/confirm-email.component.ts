import {Component, OnInit} from '@angular/core';
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpClientService} from "../../_service/http/client/http-client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs";
import {LocalStoreService} from "../../_service/store/local-store.service";

@Component({
  selector: 'app-confirm-email',
  templateUrl: './confirm-email.component.html',
  styleUrls: ['./confirm-email.component.css']
})
export class ConfirmEmailComponent implements OnInit{

  private token: string = '';

  public responseServer: boolean = true;

  public responseConfirmMessage!: boolean;

  constructor(private validateService: ValidateService, private httpClientService: HttpClientService, private route: ActivatedRoute,
              private router: Router, private localStoreService: LocalStoreService) { }


  ngOnInit() {

    if (this.localStoreService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      // Получаем токен из строки браузера
      this.route.paramMap.pipe(
        switchMap(params => params.getAll('token'))
      ).subscribe(data => this.token = data);


      if (this.validateService.patternToken(this.token)) {

        this.httpClientService.confirmEmail(this.token).subscribe({

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
