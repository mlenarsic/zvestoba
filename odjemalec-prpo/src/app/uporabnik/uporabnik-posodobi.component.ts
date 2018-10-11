import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

import {Uporabnik} from "../uporabnik/models/uporabnik";
import {UporabnikService} from "../uporabnik/services/uporabnik.service"
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import {_Uporabnik} from "../uporabnik/models/_uporabnik";

@Component({
    moduleId: module.id,
    selector: 'posodobi-uporabnik',
    templateUrl: 'uporabnik-posodobi.component.html'
})
export class UporabnikPosodobiComponent {
    uporabnik: Uporabnik = new Uporabnik;

    constructor(private uporabnikService: UporabnikService,
                private route: ActivatedRoute,
                private router: Router,
                private location: Location,
                private http: HttpClient
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.uporabnikService.getUporabnik(+params['id']))
            .subscribe(uporabnik => this.uporabnik = uporabnik);
    }
    submitForm(): void {
        let u = new _Uporabnik();
        u.ime = this.uporabnik.ime;
        u.priimek = this.uporabnik.priimek;
        u.email = this.uporabnik.email;
        u.uporabnisko_ime = this.uporabnik.uporabnisko_ime;
        u.id = this.uporabnik.id;
        this.uporabnikService.update(u);
        this.location.back();
    }

    nazaj(): void {
        this.location.back();
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}
