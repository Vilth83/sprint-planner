import { Component, OnInit } from '@angular/core';
import { GridOptions } from 'ag-grid-community';
import { ReleaseVersion } from 'src/app/models/releaseVersion.model';
import { HttpRequestBuilder } from 'src/app/shared/services/http-helper/http-request-builder.service';
import { RELEASE_VERSION_TITLE, RELEASE_VERSION_SUBTITLE } from 'src/app/shared/header-titles';

@Component({
  selector: 'app-release-version',
  templateUrl: './release-version.component.html',
  styleUrls: ['./release-version.component.css']
})
export class ReleaseVersionComponent implements OnInit {

  title: string = RELEASE_VERSION_TITLE;
  subtitle: string = RELEASE_VERSION_SUBTITLE;

  previousVersions: ReleaseVersion[];
  currentVersion: ReleaseVersion;

  gridOptions: GridOptions;
  paginationPageSize: number = 5;

  constructor(private http: HttpRequestBuilder) {
    this.gridOptions = {
      rowHeight: 50,
      defaultColDef: { sortable: true, resizable: true },
      columnDefs: [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: 'pi', field: 'pi', width: 120 },
        { headerName: 'sprint', field: 'sprint', width: 120 },
        { headerName: 'week', field: 'week', width: 120 },
        { headerName: 'version', field: 'versionName' },
        { headerName: 'releaser', field: 'releaser' }
      ],
      onFirstDataRendered: this.sizeColumnsToFit
    }
    this.getPreviousVersions();
  }

  ngOnInit() {

  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public getPreviousVersions() {
    this.http.get("/releases").subscribe((versions: ReleaseVersion[]) => {
      versions.forEach(version => ReleaseVersionComponent.setVersionName(version));
      this.previousVersions = versions;
    });
  }

  static setVersionName(version: ReleaseVersion) {
    version.versionName = "v" + version.pi + "." + version.sprint + "." + version.week + ".0";
  }
}
