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
  getLabelFunction: any;
  btnClass: string;

  agInit(params: any): void {
    this.params = params;
    this.label = this.params.label || null;
    this.btnClass = this.params.btnClass || 'btn btn-primary';
    this.getLabelFunction = this.params.getLabelFunction;

    if (this.getLabelFunction && this.getLabelFunction instanceof Function) {
      console.log(this.params);
      this.label = this.getLabelFunction(params.data);
    }

  }

  refresh(_params?: any): boolean {
    return true;
  }

  getGui() {
    return null;
  }

  onClick($event: any) {
    if (this.params.onClick instanceof Function) {
      const params = {
        event: $event,
        rowData: this.params.node.data
      }
      this.params.onClick(params);

    }
  }
}
