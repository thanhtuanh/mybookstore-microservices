import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component'; // ✅ Wichtig!

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, NavbarComponent], // ✅ NavbarComponent hier importieren!
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Thanh-Bookstore';
}
// Hier wird die Navbar-Komponente in der AppComponent verwendet.
