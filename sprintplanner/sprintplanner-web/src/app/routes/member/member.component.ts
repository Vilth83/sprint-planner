import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  view: string;
  isCreationCollapsed = false;
  isModificationCollapsed = false;

  constructor() { }

  ngOnInit() {
  }

  public changeView(params: string) {
    this.view = params;
  }

}
