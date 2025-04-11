import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Book {
  id: number;
  title: string;
  author: string;
  isbn: string;
  description: string;
  publicationDate: string;
  price: number;
  pages: number;
  genre: string;
  inStock: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private readonly apiUrl = 'http://localhost:8082/api/books';

  constructor(private readonly http: HttpClient) { }

  // Hilfsmethode für HTTP-Header mit Authentifizierung
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });
  }

  // 📚 Alle Bücher abrufen
  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }

  // 📖 Ein einzelnes Buch per ID abrufen
  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }

  // ➕ Neues Buch erstellen
  createBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, book, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }

  // ✏️ Buch aktualisieren
  updateBook(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, book, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }

  // 🗑️ Buch löschen
  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }
}
