//frontend_barberia/src/app/services/usuarios.service.spec.ts

import { TestBed } from '@angular/core/testing';
import { UsuariosService } from './usuarios.service';

describe('UsuariosService', () => {
    let service: UsuariosService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(UsuariosService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
