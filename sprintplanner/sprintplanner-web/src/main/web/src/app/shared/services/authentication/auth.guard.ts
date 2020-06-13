import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import { User } from 'src/app/models/user.model';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot) {
    const user: User = this.authenticationService.decodeUser();
    if (user) {
      if (!route.data.roles || user.hasRole(route.data.roles)) {
        return true;
      }
    }
    this.router.navigate(['/']);
    return false;
  }
}
