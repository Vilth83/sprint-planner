import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/models/member/member.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-member-creation',
  templateUrl: './member-creation.component.html',
  styleUrls: ['./member-creation.component.css']
})
export class MemberCreationComponent implements OnInit {

  private member: Member;

  constructor() { }

  ngOnInit() {
  }

  onSubmit(member: NgForm) {
     this.member = member.value;
   }

}
