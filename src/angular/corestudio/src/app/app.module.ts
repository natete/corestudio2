import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from './core/core.module';
import { LoginModule } from './login/login.module';
import { Http, HttpModule, RequestOptions, XHRBackend } from '@angular/http';
import { httpFactory } from './core/http/http.factory';
import { TokenService } from './core/auth/token.service';
import { ContainerModule } from './container/container.module';
import { SpinnerService } from './core/spinner/spinner.service';
import { InboxModule } from './inbox/inbox.module';
import { Router } from '@angular/router';
import { ClientModule } from './client/client.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    CoreModule,
    HttpModule,
    ClientModule,
    ContainerModule,
    InboxModule,
    LoginModule
  ],
  providers: [
    {
      provide: Http,
      useFactory: httpFactory,
      deps: [XHRBackend, RequestOptions, Router, TokenService, SpinnerService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
