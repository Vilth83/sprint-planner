import { Component, OnInit, Input } from '@angular/core';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { JobUpdateDto } from 'src/app/models/job-update-dto.model';

@Component({
  selector: 'app-job-configuration',
  templateUrl: './job-configuration.component.html',
  styleUrls: ['./job-configuration.component.css']
})
export class JobConfigurationComponent implements OnInit {

  releaserStatus: boolean;
  testerStatus: boolean;
  supportStatus: boolean;
  private url : string;
  @Input() job : string = "";
  @Input() icon = "";

  constructor(private http: HttpRequestBuilder) {
  }

  ngOnInit() {
    this.url = "/jobs/" + this.job;
    this.getJobStatuses();

  }

  private getJobStatuses() {
    this.http.get(this.url + "?task=releaser" ).subscribe(status =>
      this.releaserStatus = status.active
    )

    this.http.get(this.url + "?task=support").subscribe(status =>
      this.supportStatus = status.active
    )

    this.http.get(this.url + "?task=tester").subscribe(status =>
      this.testerStatus = status.active
    )
  }


  activate($event, task: string) {
    this.toggleJob(task);
  }

  private toggleJob(task: string) {
    if (task === 'releaser') {
      this.releaserStatus = !this.releaserStatus;
      this.saveStatus(this.releaserStatus, task);
    }


    if (task === 'tester') {
      this.testerStatus = !this.testerStatus;
      this.saveStatus(this.testerStatus, task);
    }

    if (task === 'support') {
      this.supportStatus = !this.supportStatus;
      this.saveStatus(this.supportStatus, task);
    }
  }

  private saveStatus(status: boolean, task: string) {
    const jobUpdateDto = new JobUpdateDto(this.job, task, status);
    this.http.put("/jobs", jobUpdateDto).subscribe();
  }
}
