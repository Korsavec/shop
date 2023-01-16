import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goUser() {
    this.router.navigate(['/login/user']).then(() => {});
  }

  goSellerPerson() {
    this.router.navigate(['/login/sellerPerson']).then(() => {});
  }

}
