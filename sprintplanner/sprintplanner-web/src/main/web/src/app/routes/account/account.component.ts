import { Component, OnInit } from '@angular/core';
import { ACCOUNT_TITLE, ACCOUNT_SUBTITLE } from 'src/app/shared/header-titles';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  title: string = ACCOUNT_TITLE;
  subtitle: string = ACCOUNT_SUBTITLE;

  constructor() { }

  ngOnInit() {
  }

}
