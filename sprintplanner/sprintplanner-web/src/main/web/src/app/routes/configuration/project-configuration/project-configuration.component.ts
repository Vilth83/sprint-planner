import { Component, OnInit } from '@angular/core';
import { Config } from 'src/app/shared/services/config';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';

@Component({
  selector: 'app-project-configuration',
  templateUrl: './project-configuration.component.html',
  styleUrls: ['./project-configuration.component.css']
})
export class ProjectConfigurationComponent implements OnInit {

  project: any = {};

  constructor(private http: HttpRequestBuilder) {
    this.getProject();
  }

  ngOnInit() {
  }

  private getProject() {
    this.http.get(Config.endpoints.project).subscribe(project => {
      this.project = project
    })
  }

  public saveProject(project: any) {
    this.http.post(Config.endpoints.project, project).subscribe();
  }
}
