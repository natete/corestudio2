import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientsListComponent } from './clients-list/clients-list.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { ClientService } from './client.service';
import { ClientFormComponent } from './client-form/client-form.component';
import { ClientContainerComponent } from './client-container/client-container.component';
import { CoreModule } from '../core/core.module';

@NgModule({
  imports: [
    CommonModule,
    CoreModule,
    SharedComponentsModule
  ],
  declarations: [ClientsListComponent, ClientFormComponent, ClientContainerComponent],
  providers: [ClientService]
})
export class ClientModule {
}
