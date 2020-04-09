import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';
import { MemberComponent } from './routes/member/member.component';
import { ReleaserComponent } from './routes/releaser/releaser.component';
import { TesterComponent } from './routes/tester/tester.component';
import { SupportComponent } from './routes/support/support.component';
import { ReleaseVersionComponent } from './routes/release-version/release-version.component';
import { ConfigurationComponent } from './routes/configuration/configuration.component';



export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'members', component: MemberComponent },
  { path: 'releasers', component: ReleaserComponent },
  { path: 'releases', component: ReleaseVersionComponent },
  { path: 'testers', component: TesterComponent },
  { path: 'supports/bangalore', component: SupportComponent },
  { path: 'supports/paris', component: SupportComponent },
  { path: 'configuration', component: ConfigurationComponent }
];

@NgModule({
  exports: [RouterModule]
})
export class AppRoutingModule { }
