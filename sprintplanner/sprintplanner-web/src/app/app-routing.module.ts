import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';
import { MemberComponent } from './routes/member/member.component';
import { ReleaserComponent } from './routes/releaser/releaser.component';
import { TesterComponent } from './routes/tester/tester.component';



export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'members', component: MemberComponent },
  { path: 'releasers', component: ReleaserComponent },
  { path: 'testers', component: TesterComponent}
];

@NgModule({
  exports: [RouterModule]
})
export class AppRoutingModule { }
