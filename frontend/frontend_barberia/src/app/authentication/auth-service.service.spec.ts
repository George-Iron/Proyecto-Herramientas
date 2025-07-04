//frontend_barberia/src/app/authentication/auth-service.service.spec.ts

import { TestBed } from '@angular/core/testing';
import { AuthServiceService } from './auth-service.service';

describe('AuthServiceService', () => {
    let service: AuthServiceService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AuthServiceService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
