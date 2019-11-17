import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule }    from '@angular/forms';

import { AppComponent } from './app.component';
import { CompanyService } from './company/company.service';
import { CompanyComponent } from './company/company.component';
import { CompanyListComponent } from './company/companyList.component';
import { PageNotFoundComponent } from './others/pageNotFound.component';
import { HomeComponent } from './company/home.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgFlashMessagesModule } from 'ng-flash-messages';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter'; 
import { Ng2OrderModule } from 'ng2-order-pipe'; 

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'summary', component: CompanyListComponent },
  { path: 'edit/:id', component: CompanyComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent, CompanyComponent,CompanyListComponent, HomeComponent, PageNotFoundComponent
  ],
  imports: [
    NgbModule,BrowserModule, HttpModule,Ng2SearchPipeModule,Ng2OrderModule,FormsModule,NgxPaginationModule,NgSelectModule, RouterModule.forRoot(appRoutes),ReactiveFormsModule, NoopAnimationsModule,NgFlashMessagesModule.forRoot()
  ],
  providers: [CompanyService],
  bootstrap: [AppComponent]
})
export class AppModule 
{ 
  
}