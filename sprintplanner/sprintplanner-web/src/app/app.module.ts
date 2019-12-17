import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';


import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ModalModule } from 'ngx-bootstrap/modal';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { RouterModule } from '@angular/router';
import { routes } from './app-routing.module';
import { HomeComponent } from './routes/home/home.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { MemberComponent } from './routes/member/member.component';
import { MemberCreationComponent } from './routes/member/member-creation/member-creation.component';
import { InformationModalComponent } from './shared/components/information-modal/information-modal.component';
import { MemberModificationComponent } from './routes/member/member-modification/member-modification.component';
import { ButtonRendererComponent } from './shared/components/button-renderer.component';
import { ConfirmationModalComponent } from './shared/components/confirmation-modal/confirmation-modal.component';
import { ReleaserComponent } from './routes/releaser/releaser.component';
import { ManageCandidateComponent } from './shared/components/candidate/manage-candidate/manage-candidate.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MemberComponent,
    MemberCreationComponent,
    InformationModalComponent,
    MemberModificationComponent,
    ButtonRendererComponent,
    ConfirmationModalComponent,
    ReleaserComponent,
    ManageCandidateComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    AngularFontAwesomeModule,
    BsDropdownModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    CollapseModule.forRoot(),
    AgGridModule.withComponents([ButtonRendererComponent])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
