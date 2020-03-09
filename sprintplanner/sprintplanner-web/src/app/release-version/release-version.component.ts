import { Component, OnInit } from '@angular/core';
import { RELEASE_VERSION_TITLE, RELEASE_VERSION_SUBTITLE } from '../shared/header-titles';
import { ReleaseVersion } from '../models/releaseVersion.model';
import { GridOptions } from 'ag-grid-community';
import { HttpRequestBuilder } from '../shared/services/http-helper/http-request-builder.service';

@Component({
  selector: 'app-release-version',
  templateUrl: './release-version.component.html',
  styleUrls: ['./release-version.component.css']
})
export class ReleaseVersionComponent implements OnInit {

  title: string = RELEASE_VERSION_TITLE;
  subtitle: string = RELEASE_VERSION_SUBTITLE;

  previousVersions: ReleaseVersion[];
  currentVersion: ReleaseVersion[];

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
        { headerName: 'releaser', field: 'assigneeName' }
      ],
      onFirstDataRendered: this.sizeColumnsToFit
    }
    this.getCurrentVersion();
    this.getPreviousVersions();
  }

  ngOnInit() {

  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public getCurrentVersion() {
    this.http.get("/releases/last").subscribe((version: ReleaseVersion) => {
      this.setVersionName(version);
      this.currentVersion = [version];
    });
  }

  public getPreviousVersions() {
    this.http.get("/releases").subscribe((versions: ReleaseVersion[]) => {
      versions.map(version => this.setVersionName(version));
      this.previousVersions = versions;
    });
  }

  private setVersionName(version: ReleaseVersion) {
    version.versionName = "v" + version.pi + "." + version.sprint + "." + version.week + ".0";
    version.assigneeName = version.assignee.member.firstname + " " + version.assignee.member.lastname;
  }
}