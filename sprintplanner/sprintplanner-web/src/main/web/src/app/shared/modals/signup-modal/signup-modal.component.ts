import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap/modal';
import { UserCredentials } from 'src/app/models/user-credentials.model';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { UserCreateDto } from 'src/app/models/user-create-dto.model';

@Component({
  selector: 'app-signup-modal',
  templateUrl: './signup-modal.component.html',
  styleUrls: ['./signup-modal.component.css']
})
export class SignupModalComponent implements OnInit {

  user: UserCredentials = new UserCredentials('', '');
  modalRef: BsModalRef;
  private config: ModalOptions;
  @ViewChild('signup') template: TemplateRef<any>;
  differentPassword: string = "";
  shortPassword: string = "";
  usernameError: string;

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
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  public submit(inputs: any) {
    const user = new UserCreateDto(inputs.username, inputs.password, inputs.firstname, inputs.lastname)
    if(this.checkInputs()) {
    this.authService.createUser(user);
  }
  }

  private clearErrorMessage() {
    this.shortPassword = "";
    this.differentPassword = "";
    this.usernameError = "";
  }
  closeModal() {
    this.clearErrorMessage();
    this.modalRef.hide();
  }

  checkUsername(username: string) {
    this.usernameError = "";
    this.authService.usernameIsUnique(username).subscribe(valid => {
      if (!valid) {
        this.usernameError = "this username is already used"
      }
    });
  }

  checkLength(password: string) {
    this.shortPassword = "";
    const valid = password.length >= 8;
    if (!valid) {
      this.shortPassword = "Password is too small. Please enter at least 8 characters"
    }
  }

  checkPasswords(user) {
    this.checkLength(user.password);
    this.checkConfirmationPassword(user);
  }
  checkConfirmationPassword(user: any) {
    this.differentPassword = "";
    if (user.password !== user.passwordCheck) {
      this.differentPassword = "Passwords does not match, please check your inputs."
    }
  }

  checkInputs() : boolean{
    this.checkPasswords(this.user);
    this.checkLength(this.user.password);
    this.checkUsername(this.user.username);
    return this.shortPassword + this.differentPassword + this.usernameError === "";
  }
}
