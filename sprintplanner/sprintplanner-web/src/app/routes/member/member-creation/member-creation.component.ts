import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Member } from 'src/app/models/member/member.model';
import { InformationModalComponent } from 'src/app/shared/information-modal/information-modal.component';

@Component({
  selector: 'app-member-creation',
  templateUrl: './member-creation.component.html',
  styleUrls: ['./member-creation.component.css']
})
export class MemberCreationComponent {

  private member: Member;
  private url = 'http://localhost:8080/api/members';
  private http: HttpClient;

  private title: string;
  private message: string;
  @ViewChild('info') private infoModal: InformationModalComponent;

  constructor(http: HttpClient) {
    this.http = http;
  }


  onSubmit(inputs: NgForm) {
    this.member = inputs.value;

    this.http.post(this.url, this.member).subscribe(() => {
      const message = this.member.firstname + ' ' +
        this.member.lastname +
        ' (email : ' + this.member.email + ')' +
        ' has been successfully created :)';
      this.openInfoModal('success ! ', message);

    }, (error) => {
      console.log(error);
      this.openInfoModal('Something went wrong :(', 'the new member has not been created, please check fields.');
    });
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }
}
