import { Component, ViewChild } from '@angular/core';
import { LoginModalComponent } from './shared/modals/index';
import { AuthenticationService } from './shared/services/authentication/authentication.service';
import { SignupModalComponent } from './shared/modals/signup-modal/signup-modal.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService: AuthenticationService) {

  }

  ngOnInit() {
  }

  @ViewChild('login') private loginModal: LoginModalComponent;
  @ViewChild('signup') private signupModal : SignupModalComponent;

  public signin() {
    this.loginModal.openModal(this.loginModal.template);
  }

  public submitAccount() {
    this.signupModal.openModal(this.signupModal.template);
  }

  public logout() {
    this.authService.logout();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }
}
