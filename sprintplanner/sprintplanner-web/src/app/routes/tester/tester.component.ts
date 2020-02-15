import { Component, OnInit } from '@angular/core';
import { TESTER_TITLE, TESTER_SUBTITLE } from 'src/app/shared/header-titles';

@Component({
  selector: 'app-tester',
  templateUrl: './tester.component.html',
  styleUrls: ['./tester.component.css']
})
export class TesterComponent implements OnInit {

  title: string = TESTER_TITLE;
  subtitle: string = TESTER_SUBTITLE;

  constructor() { }

  ngOnInit() {
  }

}
