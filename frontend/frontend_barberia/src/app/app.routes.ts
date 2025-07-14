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
        canActivate: [preventLoggedInAccessGuard]
    },

    // 3. Ruta del Panel Principal (protegida): requiere autenticación
    {
        path: 'panel',
        component: LoyoutComponent, 
        canActivate: [authGuard],   
        children: [
            
            { path: '', redirectTo: 'inicio', pathMatch: 'full' },
            {
                path: 'inicio', 
                component: InicioComponent 
            },
            {
                path: 'usuarios', 
                component: UsuariosComponent
            },
            {
                path: 'usuarios/crear', 
                component: UsuariosFormulariosComponent
            },
            {
                path: 'usuarios/editar/:id', 
                component: UsuariosFormulariosComponent
            },
        ]
    },

    // 4. Ruta comodín: si la URL no existe, redirige a /login (o a una página 404)
    { path: '**', redirectTo: 'login' }
];
