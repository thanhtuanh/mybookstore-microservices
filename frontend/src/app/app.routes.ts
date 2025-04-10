import { Routes } from '@angular/router';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { BookCreateComponent } from './components/book-create/book-create.component';
import { BookEditComponent } from './components/book-edit/book-edit.component';
import { LoginComponent } from './components/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

export const routes: Routes = [
  { path: '', redirectTo: 'welcome', pathMatch: 'full' },         // ✅ Nur EIN Redirect!
  { path: 'welcome', component: WelcomeComponent },               // ✅ Zeigt Startseite
  { path: 'login', component: LoginComponent },                   // ✅ Login-Seite
  { path: 'books', component: BookListComponent },                // ✅ Buchübersicht
  { path: 'books/create', component: BookCreateComponent },       // ➕ Neues Buch
  { path: 'books/:id', component: BookDetailComponent },          // 🔍 Einzelbuch-Anzeige
  { path: 'books/:id/edit', component: BookEditComponent }        // ✏️ Buch bearbeiten
];
