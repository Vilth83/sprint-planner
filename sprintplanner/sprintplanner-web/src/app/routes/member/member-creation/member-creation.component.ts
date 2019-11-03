import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Member } from 'src/app/models/member/member.model';
import { InformationModalComponent } from 'src/app/shared/information-modal/information-modal.component';
import { HttpRequestBuilder } from 'src/app/shared/services/http-request-builder.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-member-creation',
  templateUrl: './member-creation.component.html',
  styleUrls: ['./member-creation.component.css']
})
export class MemberCreationComponent {

  private member: Member;
  private endpoint = '/members';

  private title: string;
  private message: string;
  @ViewChild('info') private infoModal: InformationModalComponent;

  constructor(private httpBuilder: HttpRequestBuilder) {
  }


  onSubmit(inputs: NgForm) {
    this.member = inputs.value;
    const request: Observable<any> = this.httpBuilder.post(this.endpoint, this.member);

    request.subscribe(() => {
      const name = this.member.firstname + ' ' + this.member.lastname;
      const message = name + ' (email : ' + this.member.email + ')' + ' has been successfully created :)';
      this.openInfoModal('success ! ', message);

    }, (error) => {
      const errors = error.error.errors;
      let errMessage = "";
      errors.forEach(function(err:any) {
        errMessage = errMessage + err.field + " " + err.defaultMessage + ". ";
      });
      this.openInfoModal('Something went wrong :( = '+ errors.length + " errors", errMessage);
    });
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }
}
