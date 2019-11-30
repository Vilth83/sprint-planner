import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.css']
})
export class ConfirmationModalComponent implements OnInit {

  private modalRef: BsModalRef;
  @Input() action: string;
  @Input() inputs: string;
  @ViewChild('confirmation') template: TemplateRef<any>;

  private confirmed: () => void;
  private declined: () => void;


  constructor(public modalService: BsModalService) { }

  ngOnInit() {
    //
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  public setConfirmation(confirmed: () => void, declined: () => void) {
    this.confirmed = confirmed;
    this.declined = declined;
  }

  confirm() {
    this.confirmed();
    this.modalRef.hide()
  }
  decline() {
    this.declined();
    this.modalRef.hide();
  }

}
