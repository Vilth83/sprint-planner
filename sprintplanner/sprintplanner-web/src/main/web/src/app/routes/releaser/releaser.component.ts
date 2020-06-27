import { Component, OnInit } from '@angular/core';
import { RELEASER_TITLE, RELEASER_SUBTITLE } from 'src/app/shared/header-titles';

@Component({
  selector: 'app-releaser',
  templateUrl: './releaser.component.html',
  styleUrls: ['./releaser.component.css']
})
export class ReleaserComponent implements OnInit {

  title : string = RELEASER_TITLE;
  subtitle: string = RELEASER_SUBTITLE;
  constructor() { }

  ngOnInit() {
  }
}
