import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';       // ✅ Für *ngIf
import { FormsModule } from '@angular/forms';         // ✅ Für [(ngModel)]
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [
    CommonModule,     // ✅ wichtig für *ngIf
    FormsModule       // ✅ wichtig für ngModel
  ]
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {}

  onLogin(): void {
    console.log('🔐 Login attempt for:', this.username);

    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('✅ Login success:', response);
        localStorage.setItem('token', response.token);
        localStorage.setItem('username', response.username);
        this.router.navigate(['/books']);
      },
      error: (error) => {
        console.error('❌ Login failed:', error);
        this.errorMessage = 'Login fehlgeschlagen. Bitte überprüfe Benutzername und Passwort.';
      }
    });
  }
}
