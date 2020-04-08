import { Component, OnInit } from '@angular/core';
import { SUPPORT_TITLE, SUPPORT_SUBTITLE } from 'src/app/shared/header-titles';
import { Router } from '@angular/router';
import { Shift } from 'src/app/models/shift.model';

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
      this.shift = Shift.PAR;
    } else if(url.includes("bangalore")) {
      this.shift = Shift.BGL;
    }
  }

}
