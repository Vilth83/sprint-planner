import { Component, ViewChild } from '@angular/core';
import { LoginModalComponent } from './shared/modals/index';
import { AuthenticationService } from './shared/services/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService: AuthenticationService) {}

  @ViewChild('login') private loginModal: LoginModalComponent;

  public signin() {
    this.loginModal.openModal(this.loginModal.template);
  }

  public logout() {
    this.authService.logout();
  }
}
