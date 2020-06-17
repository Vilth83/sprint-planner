import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap/modal';
import { UserCredentials } from 'src/app/models/user-credentials.model';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent implements OnInit {

  user: UserCredentials = new UserCredentials('', '');
  modalRef: BsModalRef;
  private config: ModalOptions;
  errorMessage = "";
  @ViewChild('login') template: TemplateRef<any>;

  constructor(
    private modalService: BsModalService,
    private authService: AuthenticationService
  ) {
    this.config = {
      backdrop: false,
      ignoreBackdropClick: true,
      keyboard: false
    }
  }

  ngOnInit() {
  }
  
  ngOnChange() {
    this.clearErrorMessage();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  public submit(user: UserCredentials) {
    this.authService.login(user)
      .subscribe(
        () => {
          this.closeModal();
          window.location.reload()
        },
        () => this.errorMessage = "invalid credentials !"
      )
  }

  private clearErrorMessage() {
    this.errorMessage = "";
  }
  closeModal() {
    this.clearErrorMessage();
    this.modalRef.hide();
  }
}
