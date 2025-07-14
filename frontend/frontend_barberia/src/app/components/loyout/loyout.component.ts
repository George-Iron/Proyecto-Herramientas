//frontend_barberia/src/app/components/loyout/loyout.component.ts

import { Component } from '@angular/core';
import { RouterLink, RouterOutlet, RouterLinkActive } from '@angular/router'; // <-- AÑADIR RouterLinkActive
import { AuthServiceService } from '../../authentication/auth-service.service';

@Component({
    selector: 'app-loyout',
    standalone: true, // <-- AÑADIR
    imports: [RouterLink, RouterOutlet, RouterLinkActive], // <-- AÑADIR RouterLinkActive
    templateUrl: './loyout.component.html',
    styleUrl: './loyout.component.css'
})

export class LoyoutComponent {
    constructor(private authService: AuthServiceService) { }
    //considerar servicios esenciales para el header
    // y tambien la reactividad en caso ser necesario
    //por mientras añadir estructura html

    logout() {
        this.authService.logout();
    }
}
