import { Component, OnInit, Input, TemplateRef, ViewChild } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-info-modal',
  templateUrl: './information-modal.component.html',
  styleUrls: ['./information-modal.component.css']
})
export class InformationModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() title: string;
  @Input() message: string;
  @ViewChild('info') template: TemplateRef<any>;


  constructor(public modalService: BsModalService) { }

  ngOnInit() {
    //
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
}
