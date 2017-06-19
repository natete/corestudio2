import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MdButtonModule, MdCardModule, MdIconModule, MdIconRegistry, MdInputModule, MdMenuModule,
  MdProgressSpinnerModule
} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MdCardModule,
    MdIconModule,
    MdInputModule,
    MdMenuModule,
    MdProgressSpinnerModule
  ],
  exports: [
    MdButtonModule,
    MdCardModule,
    MdIconModule,
    MdInputModule,
    MdMenuModule,
    MdProgressSpinnerModule
  ]
})
export class MaterialComponentsModule {


  constructor(private mdIconRegistry: MdIconRegistry) {
    this.mdIconRegistry.setDefaultFontSetClass('fa');
  }
}
