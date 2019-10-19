import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Member } from 'src/app/models/member/member.model';

@Component({
  selector: 'app-member-creation',
  templateUrl: './member-creation.component.html',
  styleUrls: ['./member-creation.component.css']
})
export class MemberCreationComponent {

  private member: Member;
  private url: string = "http://localhost:8080/api/members";
  http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }


  onSubmit(inputs: NgForm) {
    this.member = inputs.value;

    this.http.post(this.url, this.member).subscribe( () => {
      // TODO: confirm persistence

    }, (error) => {
      // TODO: handle error

    }
    );
  }

}
