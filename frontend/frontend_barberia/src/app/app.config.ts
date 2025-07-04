//frontend_barberia/src/app/app.config.ts

import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http'; // <-- IMPORTAR

import { routes } from './app.routes';
import { authInterceptor } from './authentication/auth.interceptor'; // <-- IMPORTAR

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    // Agrega el provider para HttpClient y registra tu interceptor
    provideHttpClient(withInterceptors([authInterceptor])) // <-- AÑADIR ESTA LÍNEA
  ]
};
