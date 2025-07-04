//frontend_barberia/src/app/pages/inicio/inicio.component.ts

import { Component } from '@angular/core';

@Component({
    selector: 'app-inicio',
    standalone: true, // <-- AÑADIR
    imports: [],
    templateUrl: './inicio.component.html',
    styleUrl: './inicio.component.css'
})
export class InicioComponent {
    // En el futuro, podrías inyectar servicios aquí para obtener datos
    // constructor(private reportesService: ReportesService) {}
}