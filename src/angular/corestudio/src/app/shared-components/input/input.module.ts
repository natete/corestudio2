import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MdDatepickerModule, MdIconModule, MdInputModule, MdNativeDateModule, MdSlideToggleModule } from '@angular/material';
import { NgxErrorsModule } from '@ultimate/ngxerrors';
import { ReactiveFormsModule } from '@angular/forms';
import 'hammerjs';
import { InputComponent } from './input.component';
import { DatepickerComponent } from './datepicker/datepicker.component';
import { ToggleComponent } from './toggle/toggle.component';
import { InputTextComponent } from './input-text/input-text.component';
import { InputTextAreaComponent } from './input-textarea/input-textarea.component';

@NgModule({
  imports: [
    CommonModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdIconModule,
    MdInputModule,
    MdSlideToggleModule,
    NgxErrorsModule,
    ReactiveFormsModule
  ],
  declarations: [
    InputComponent,
    DatepickerComponent,
    ToggleComponent,
    InputTextComponent,
    InputTextAreaComponent
  ],
  exports: [
    InputComponent,
    DatepickerComponent,
    ToggleComponent,
    InputTextComponent,
    InputTextAreaComponent
  ]
})
export class InputModule {
}
