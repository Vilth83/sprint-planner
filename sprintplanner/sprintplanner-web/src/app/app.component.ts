import { Component, ViewChild } from '@angular/core';
import { LoginModalComponent } from './shared/modals/index';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor() {}

  @ViewChild('login') private loginModal: LoginModalComponent;



  public signin() {
    console.log("login modal : ", this.loginModal)
    this.loginModal.openModal(this.loginModal.template);
  }

}
