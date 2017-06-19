import { NgModule } from '@angular/core';
import { InboxPageComponent } from './inbox.component';
import { InboxService } from './inbox.service';
import { CoreModule } from '../core/core.module';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { MessageComponent } from './message/message.component';

@NgModule({
  imports: [
    CoreModule,
    SharedComponentsModule
  ],
  declarations: [InboxPageComponent, MessageComponent],
  providers: [InboxService]
})
export class InboxModule {
}
