import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from '../../login/login-page.component';
import { AuthGuard } from './auth.guard';
import { ContainerComponent } from '../../container/container.component';
import { InboxPageComponent } from '../../inbox/inbox.component';
import { AuthModule } from '../auth/auth.module';
import { ClientsListComponent } from '../../client/clients-list/clients-list.component';
import { ClientContainerComponent } from '../../client/client-container/client-container.component';
import { ClientFormComponent } from '../../client/client-form/client-form.component';

const routes: Routes = [
  {
    path: '',
    component: ContainerComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'inbox', pathMatch: 'full' },
      { path: 'inbox', component: InboxPageComponent },
      {
        path: 'clients', component: ClientContainerComponent,
        children: [
          { path: '', component: ClientsListComponent },
          { path: 'new', component: ClientFormComponent }
        ]
      },

    ]
  },
  { path: 'login', component: LoginPageComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AuthModule
  ],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule {
}
