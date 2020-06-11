import { Component, OnInit, Input } from '@angular/core';
import { JobUpdateDto } from 'src/app/models/job-update-dto.model';
import { Config } from 'src/app/shared/services/config';
import { JobStatus } from 'src/app/models/job-status.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';

@Component({
  selector: 'app-job-configuration',
  templateUrl: './job-configuration.component.html',
  styleUrls: ['./job-configuration.component.css']
})
export class JobConfigurationComponent implements OnInit {

  releaserStatus: boolean;
  testerStatus: boolean;
  supportStatus: boolean;
  private endpoint : string;
  @Input() job : string = "";
  @Input() icon = "";

  constructor(private http: HttpRequestBuilder) {
  }

  ngOnInit() {
    this.endpoint = Config.endpoints.job + '/' + this.job
    this.getJobStatuses();

  }

  private getJobStatuses() {
    this.http.get(this.endpoint + Config.params.releaser).subscribe((job: JobStatus) =>
      this.releaserStatus = job.active
    )

    this.http.get(this.endpoint + Config.params.support).subscribe((job: JobStatus) =>
      this.supportStatus = job.active
    )

    this.http.get(this.endpoint + Config.params.tester).subscribe((job: JobStatus) =>
      this.testerStatus = job.active
    )
  }


  activate(_$event: any, task: string) {
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
