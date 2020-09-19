import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';

@Component({
  selector: 'button-renderer',
  template: `
    <button class="{{btnClass}}" type="button" (click)="onClick($event)">{{label}}</button>
    `
})

export class ButtonRendererComponent implements ICellRendererAngularComp {
  params: any;
  label: string;
  getLabelFunction: Function;
  btnClass: string;

  agInit(params: any): void {
    this.params = params;
    this.label = this.params.label || null;
    this.btnClass = this.params.btnClass || 'btn btn-primary';
    this.getLabelFunction = this.params.getLabelFunction;

    if (this.getLabelFunction) {
      this.label = this.getLabelFunction(params.data);
    }

    if(params.value && params.value === true || params.value === "ROLE_ADMIN") {
      this.btnClass = 'btn btn-primary fa fa-check-square fa-lg'
    }

    if(params.value === false || params.value === "ROLE_USER") {
      this.btnClass = 'btn btn-primary fa fa-square fa-lg'
    }

  }

  refresh(_params?: any): boolean {
    return true;
  }

  onClick($event: Function) {
      const params = {
        event: $event,
        rowData: this.params.node.data
      }
      this.params.onClick(params);
    }
  }
