import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap/modal';
import { UserCredentials } from 'src/app/models/user-credentials.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Config } from '../../services/config';
import { TokenStorageService } from '../../services/token-storage.service';

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

  constructor(public modalService: BsModalService, public http: HttpClient, public tokenStore: TokenStorageService) {
  this.config = {
    backdrop: false,
    ignoreBackdropClick: true,
    keyboard: false
  }}

  ngOnInit() {
  }
  ngOnChange() {
    this.clearErrorMessage();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  public submit(user: UserCredentials) {
    this.login(user)
      .subscribe(
        token => {
          this.tokenStore.saveToken(token);
          this.closeModal();
        }
        , err => {
          this.errorMessage = "invalid credentials !";

          console.log(err)
        }
      )
  }

  private login = (data: UserCredentials): Observable<any> => {
    const header = Config.grantType.password + '&username=' + data.username + '&password=' + data.password + Config.clientId;
    return this.http.post<any>(Config.uris.token, header, Config.httpOptions.formUrlEncoded);
  }

  clearErrorMessage() {
    this.errorMessage= "";
  }
  closeModal() {
    this.errorMessage = "";
    this.modalRef.hide();
  }
}
