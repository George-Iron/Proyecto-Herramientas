//frontend_barberia/src/app/app.routes.ts

import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LoyoutComponent } from './components/loyout/loyout.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { authGuard, preventLoggedInAccessGuard } from './authentication/auth.guard';
import { UsuariosComponent } from './pages/User/usuarios/usuarios.component';
import { UsuariosFormulariosComponent } from './pages/User/usuarios-formularios/usuarios-formularios.component';

export const routes: Routes = [
    // 1. Ruta por defecto: si no hay nada, redirige a /login
    { path: '', redirectTo: 'login', pathMatch: 'full' },

    // 2. Ruta de Login: accesible solo si NO estás autenticado
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [preventLoggedInAccessGuard] // Evita que un usuario logueado vea el login
    },

    // 3. Ruta del Panel Principal (protegida): requiere autenticación
    {
        path: 'panel',
        component: LoyoutComponent, // El Layout es el contenedor principal
        canActivate: [authGuard],    // Solo accesible si estás logueado
        children: [
            // Rutas hijas que se mostrarán DENTRO del LoyoutComponent
            { path: '', redirectTo: 'inicio', pathMatch: 'full' }, // Si entras a /panel, te lleva a /panel/inicio
            {
                path: 'inicio', // La URL será /panel/inicio
                component: InicioComponent // ¡Aquí está tu página de inicio!
            },
            {
                path: 'usuarios', // La URL será /panel/usuarios
                component: UsuariosComponent
            },
            {
                path: 'usuarios/crear', // La URL será /panel/usuarios/crear
                component: UsuariosFormulariosComponent
            },
            {
                path: 'usuarios/editar/:id', // La URL será /panel/usuarios/editar/123
                component: UsuariosFormulariosComponent
            },
            // Aquí puedes agregar más rutas hijas para 'puestos', 'reportes', 'perfil', etc.
            // { path: 'puestos', component: PuestosComponent },
            // { path: 'reportes', component: ReportesComponent },
        ]
    },

    // 4. Ruta comodín: si la URL no existe, redirige a /login (o a una página 404)
    { path: '**', redirectTo: 'login' }
];
