import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderBarComponent } from './header-bar.component';
import { MdToolbarModule } from '@angular/material';
import { MaterialComponentsModule } from '../core/material-components/material-components.module';

@NgModule({
  imports: [
    CommonModule,
    MaterialComponentsModule,
    MdToolbarModule
  ],
  declarations: [HeaderBarComponent],
  exports: [HeaderBarComponent]
})
export class HeaderBarModule {
}
