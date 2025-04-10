import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class NavbarComponent {
  get isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  get username(): string | null {
    return localStorage.getItem('username');
  }

  constructor(private readonly router: Router) { }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
