import { Component, OnInit } from '@angular/core';
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
    this.http.get("/projects").subscribe(project => {
      this.project = project
      console.log(project)
    })
  }

  public saveProject(project: any) {
    console.log(project);
    this.http.post("/projects", project).subscribe();
  }

}
