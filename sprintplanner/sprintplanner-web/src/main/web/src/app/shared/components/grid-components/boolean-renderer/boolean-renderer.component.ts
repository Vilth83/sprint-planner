import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';

@Component({
  selector: 'app-boolean-renderer',
  templateUrl: './boolean-renderer.component.html',
  styleUrls: ['./boolean-renderer.component.css']
})

export class BooleanRendererComponent implements ICellRendererAngularComp {

  params: any;

  refresh(_params: any): boolean {
    return false;
  }

  agInit(params: any) : void {
    this.params = params;
  }

  isTrue():boolean {
    return this.params.value;
  }

}
