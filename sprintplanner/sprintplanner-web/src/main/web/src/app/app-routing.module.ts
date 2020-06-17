import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';
import { MemberComponent } from './routes/member/member.component';
import { ReleaserComponent } from './routes/releaser/releaser.component';
import { SupportComponent } from './routes/support/support.component';
import { ReleaseVersionComponent } from './routes/release-version/release-version.component';
import { ConfigurationComponent } from './routes/configuration/configuration.component';
import { IssueReconciliationComponent } from './routes/issue-reconciliation/issue-reconciliation.component';
import { TesterComponent } from './routes/tester/tester.component';
import { AuthGuard } from './shared/services/authentication/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'members', component: MemberComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'releasers', component: ReleaserComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'testers', component: TesterComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'releases', component: ReleaseVersionComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'issues', component: IssueReconciliationComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'supports/bangalore', component: SupportComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'supports/paris', component: SupportComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'configuration', component: ConfigurationComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] } }
];

@NgModule({
  exports: [RouterModule]
})
export class AppRoutingModule { }
