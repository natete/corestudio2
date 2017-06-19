import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './routing/app-routing.module';
import { MaterialComponentsModule } from './material-components/material-components.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxErrorsModule } from '@ultimate/ngxerrors';
import { BrowserModule } from '@angular/platform-browser';
import { SpinnerService } from './spinner/spinner.service';

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    MaterialComponentsModule,
    NgxErrorsModule,
    ReactiveFormsModule
  ],
  exports: [
    BrowserModule,
    AppRoutingModule,
    MaterialComponentsModule,
    NgxErrorsModule,
    ReactiveFormsModule
  ],
  declarations: [],
  providers: [SpinnerService]
})
export class CoreModule {
}
