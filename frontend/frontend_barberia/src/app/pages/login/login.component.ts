//frontend_barberia/src/app/pages/login/login.component.ts

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthServiceService } from '../../authentication/auth-service.service';
import { Router } from '@angular/router';
import { IonIcon } from '@ionic/angular/standalone';
import { mail, lockClosed } from 'ionicons/icons';

@Component({
    selector: 'app-login',
    standalone: true, // <-- AÑADIR
    imports: [ReactiveFormsModule, IonIcon], // IonIcon ya está en imports
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
    formularioLogin!: FormGroup;
    //iconos
    public mailIcon = mail;
    public lockClosedIcon = lockClosed;
    constructor(public authService: AuthServiceService, public router: Router) { }
    ngOnInit(): void {
        this.formularioLogin = new FormGroup({
            username: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required])
        });
    }
    login() {
        console.log(this.formularioLogin.value);
        this.authService.login(this.formularioLogin.value).subscribe({
            next: (data) => {
                this.router.navigate(['/panel']);
                this.formularioLogin.reset();
            },
            error: (e) => {
                console.log('Error en login', e);
            }
        })
    }
}
