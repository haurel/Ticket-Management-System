import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
})
export class AppComponent {
  title = 'Ticket Managment System';
 
  constructor(private router: Router) {
  }

  public NavigateToLogin() {
    this.router.navigate(['/p/login']);
  }
}
