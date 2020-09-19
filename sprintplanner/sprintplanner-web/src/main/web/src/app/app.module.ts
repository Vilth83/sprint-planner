import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClientXsrfModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';


import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { UiSwitchModule } from 'ngx-ui-switch';
import { RouterModule } from '@angular/router';
import { routes } from './app-routing.module';
import { HomeComponent } from './routes/home/home.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { MemberComponent } from './routes/member/member.component';
import { InformationModalComponent } from './shared/modals/information-modal/information-modal.component';
import { MemberModificationComponent } from './routes/member/member-modification/member-modification.component';
import { ButtonRendererComponent } from './shared/components/button-renderer.component';
import { ConfirmationModalComponent } from './shared/modals/confirmation-modal/confirmation-modal.component';
import { ReleaserComponent } from './routes/releaser/releaser.component';
import { ManageCandidateComponent } from './shared/components/candidate/manage-candidate/manage-candidate.component';
import { CurrentCandidateComponent } from './shared/components/candidate/current-candidate/current-candidate.component';
import { PageHeaderComponent } from './shared/components/page-header/page-header.component';
import { SupportComponent } from './routes/support/support.component';
import { ReleaseVersionComponent } from './routes/release-version/release-version.component';
import { CurrentReleaseComponent } from './routes/release-version/current-release/current-release.component';
import { ConfigurationComponent } from './routes/configuration/configuration.component';
import { ProjectConfigurationComponent } from './routes/configuration/project-configuration/project-configuration.component';
import { JobConfigurationComponent } from './routes/configuration/job-configuration/job-configuration.component';
import { LoginModalComponent } from './shared/modals/login-modal/login-modal.component';
import { IssueReconciliationComponent } from './routes/issue-reconciliation/issue-reconciliation.component';
import { TesterComponent } from './routes/tester/tester.component';
import { BooleanRendererComponent } from './shared/components/grid-components/boolean-renderer/boolean-renderer.component';
import { TokenInterceptor } from './shared/services/authentication/token-interceptor.service';
import { HttpErrorHandlerService } from './shared/services/http-error-handler.service';
import { JiraStateRendererComponent } from './shared/components/grid-components/jira-state-renderer.component';
import { SignupModalComponent } from './shared/modals/signup-modal/signup-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MemberComponent,
    InformationModalComponent,
    MemberModificationComponent,
    ButtonRendererComponent,
    ConfirmationModalComponent,
    ReleaserComponent,
    ManageCandidateComponent,
    CurrentCandidateComponent,
    PageHeaderComponent,
    SupportComponent,
    ReleaseVersionComponent,
    CurrentReleaseComponent,
    ConfigurationComponent,
    ProjectConfigurationComponent,
    JobConfigurationComponent,
    LoginModalComponent,
    IssueReconciliationComponent,
    TesterComponent,
    BooleanRendererComponent,
    JiraStateRendererComponent,
    SignupModalComponent
    ],
  imports: [
    UiSwitchModule,
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    AngularFontAwesomeModule,
    BsDropdownModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    CollapseModule.forRoot(),
    AgGridModule.withComponents([
      ButtonRendererComponent,
      BooleanRendererComponent,
      JiraStateRendererComponent
    ]),
    HttpClientXsrfModule.withOptions({
    cookieName: 'XSRF-TOKEN',
    headerName: 'X-XSRF-TOKEN',
  })
  ],
  providers: [
    HttpErrorHandlerService,
    { provide: ErrorHandler, useClass: HttpErrorHandlerService },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
