import { Component, OnInit } from '@angular/core';
import { MEMBER_TITLE, MEMBER_SUBTITLE } from 'src/app/shared/header-titles';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  view: string;

  title: string = MEMBER_TITLE;
  subtitle: string = MEMBER_SUBTITLE;

  constructor() { }

  ngOnInit() {
  }

  public changeView(params: string) {
    this.view = params;
  }

}
