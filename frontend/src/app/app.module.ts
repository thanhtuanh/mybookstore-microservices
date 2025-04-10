import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Für ngModel
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { MatPaginatorModule } from '@angular/material/paginator';  // MatPaginator importieren
import { NgxPaginationModule } from 'ngx-pagination';  // Pagination-Modul importieren

@NgModule({
  declarations: [
    // Deine Komponenten hier
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    MatPaginatorModule,
    NgxPaginationModule  // Pagination-Modul hier hinzufügen
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
