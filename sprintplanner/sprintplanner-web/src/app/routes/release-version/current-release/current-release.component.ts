import { Component, OnInit, Input } from '@angular/core';
import { ReleaseVersion } from 'src/app/models/releaseVersion.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { ReleaseVersionComponent } from '../release-version.component';
import { Config } from 'src/app/shared/services/config';

@Component({
  selector: 'app-current-release',
  templateUrl: './current-release.component.html',
  styleUrls: ['./current-release.component.css']
})
export class CurrentReleaseComponent implements OnInit {

  @Input()
  currentVersion:ReleaseVersion = new ReleaseVersion();
  constructor(private http: HttpRequestBuilder) {
    this.getCurrentVersion();
}

  ngOnInit() {
  }

  public getCurrentVersion() {
    this.http.get(Config.endpoints.releases.last).subscribe((version: ReleaseVersion) => {
      ReleaseVersionComponent.setVersionName(version);
      this.currentVersion = version;
    });
  }

}
