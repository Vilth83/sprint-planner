import { Component, OnInit } from '@angular/core';
import { SUPPORT_TITLE, SUPPORT_SUBTITLE } from 'src/app/shared/header-titles';
import { Router } from '@angular/router';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {

  title: string = SUPPORT_TITLE;
  subtitle: string = SUPPORT_SUBTITLE;

  shift: string ='';


  constructor(private router: Router) { }

  ngOnInit() {
    const url = this.router.url.toString();
    if(url.includes("paris")) {
      this.shift = "PAR";
    } else if(url.includes("bangalore")) {
      this.shift = "BGL";
    }
  }

}
