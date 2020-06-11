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



export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'members', component: MemberComponent },
  { path: 'releasers', component: ReleaserComponent },
  { path: 'testers', component: TesterComponent },
  { path: 'releases', component: ReleaseVersionComponent },
  {path: 'issues', component: IssueReconciliationComponent},
  { path: 'supports/bangalore', component: SupportComponent },
  { path: 'supports/paris', component: SupportComponent },
  { path: 'configuration', component: ConfigurationComponent }
];

@NgModule({
  exports: [RouterModule]
})
export class AppRoutingModule { }
