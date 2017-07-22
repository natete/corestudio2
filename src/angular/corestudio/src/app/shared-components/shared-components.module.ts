import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TitleComponent } from './title/title.component';
import { MaterialComponentsModule } from '../core/material-components/material-components.module';
import { TableComponent } from './table/table.component';
import { KeysPipe } from './pipes/keys.pipe';
import { InputModule } from './input/input.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialComponentsModule,
    InputModule
  ],
  declarations: [
    TitleComponent,
    TableComponent,
    KeysPipe,
  ],
  exports: [
    InputModule,
    TitleComponent,
    TableComponent
  ]
})
export class SharedComponentsModule {
}
