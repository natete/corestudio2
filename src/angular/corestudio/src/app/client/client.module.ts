import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientsListComponent } from './clients-list/clients-list.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { ClientService } from './client.service';
import { MaterialComponentsModule } from '../core/material-components/material-components.module';

@NgModule({
  imports: [
    CommonModule,
    MaterialComponentsModule,
    SharedComponentsModule
  ],
  declarations: [ClientsListComponent],
  providers: [ClientService]
})
export class ClientModule {
}
