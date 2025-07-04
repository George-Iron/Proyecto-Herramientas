//frontend_barberia/src/app/components/loyout/loyout.component.spec.ts

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoyoutComponent } from './loyout.component';

describe('LoyoutComponent', () => {
    let component: LoyoutComponent;
    let fixture: ComponentFixture<LoyoutComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [LoyoutComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(LoyoutComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
