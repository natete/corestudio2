import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TitleComponent } from './title/title.component';
import { MaterialComponentsModule } from '../core/material-components/material-components.module';
import { TableComponent } from './table/table.component';
import { KeysPipe } from './pipes/keys.pipe';

@NgModule({
  imports: [
    CommonModule,
    MaterialComponentsModule
  ],
  declarations: [
    TitleComponent,
    TableComponent,
    KeysPipe
  ],
  exports: [
    TitleComponent,
    TableComponent
  ]
})
export class SharedComponentsModule {
}
