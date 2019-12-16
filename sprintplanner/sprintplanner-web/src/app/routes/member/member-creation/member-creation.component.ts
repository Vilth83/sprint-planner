import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Member } from 'src/app/models/member/member.model';
import { InformationModalComponent } from 'src/app/shared/components/information-modal/information-modal.component';
import { Subscription } from 'rxjs';
import { MemberHttpRequest } from 'src/app/shared/services/http-helper/member-http-request.service';

@Component({
  selector: 'app-member-creation',
  templateUrl: './member-creation.component.html',
  styleUrls: ['./member-creation.component.css']
})
export class MemberCreationComponent {

  private member: Member;

  private memberCreationSubscription: Subscription;

  title: string;
  message: string;
  @ViewChild('info')
  private infoModal: InformationModalComponent;

  constructor(private http: MemberHttpRequest) {
  }


  onSubmit(inputs: NgForm) {
    this.member = inputs.value;
    const request = this.http.post(this.member);
    this.memberCreationSubscription =
      request.subscribe(() => {
        const name = this.member.firstname + ' ' + this.member.lastname;
        const message = name + ' (email : ' + this.member.email + ')' + ' has been successfully created :)';
        this.openInfoModal('success ! ', message);

      }, (error) => {
        const errors = error.error.errors;
        let errMessage = "";
        errors.forEach(function(err: any) {
          errMessage = errMessage + err.field + " " + err.defaultMessage + ". ";
        });
        this.openInfoModal('Something went wrong :( = ' + errors.length + " errors", errMessage);
      });
  }

  public openInfoModal(title: string, message: string) {
    this.title = title;
    this.message = message;
    this.infoModal.openModal(this.infoModal.template);
  }

  ngOnDestroy() {
    this.memberCreationSubscription.unsubscribe();
  }
}
