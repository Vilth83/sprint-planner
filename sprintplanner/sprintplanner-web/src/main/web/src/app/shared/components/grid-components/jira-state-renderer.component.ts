import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';

@Component({
  template: `
  <span [ngSwitch]="params.value">
    <em *ngSwitchCase='"CLOSED"' class="fa fa-check" style="color:green"></em>
    <em *ngSwitchCase='"OPENED"'class="fa fa-times" style="color:red"></em>
    <em *ngSwitchDefault class="fa fa-exclamation-triangle" style="color:orange"></em>
  </span>
`
})

export class JiraStateRendererComponent implements ICellRendererAngularComp {

  params: any;
  tooltip: string;

  refresh(_params: any): boolean {
    return false;
  }

  agInit(params: any) : void {
    this.params = params;
  }

  setTooltip() {
    console.log(this.params.value)
    switch(this.params.value) {
      case 'OPENED': this.tooltip = "Jira ticket is OPENED";
      break;
      case 'CLOSED': this.tooltip = "Jira ticket is CLOSED";
      break;
      default: this.tooltip="There is NO jira ticket associated with this commit !";

    }
  }
}
