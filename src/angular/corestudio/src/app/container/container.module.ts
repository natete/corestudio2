import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialComponentsModule } from '../core/material-components/material-components.module';
import { ContainerComponent } from './container.component';
import { SidebarModule } from '../sidebar/sidebar.module';
import { HeaderBarModule } from '../header-bar/header-bar.module';
import { CoreModule } from '../core/core.module';

@NgModule({
  imports: [
    CommonModule,
    CoreModule,
    MaterialComponentsModule,
    HeaderBarModule,
    SidebarModule
  ],
  declarations: [ContainerComponent],
  exports: [ContainerComponent]
})
export class ContainerModule {
}
