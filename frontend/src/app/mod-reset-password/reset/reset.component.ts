import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-reset',
  template: '',
  styles: []
})
export class ResetComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {

    this.router.navigate(['/']).then(() => {});
  }

}
