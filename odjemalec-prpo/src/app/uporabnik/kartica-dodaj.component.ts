import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

import {Tocke} from './models/tocke';
import {TockeService} from './services/tocke.service';
import {Uporabnik} from "./models/uporabnik";
import {UporabnikService} from "./services/uporabnik.service"
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import {_Uporabnik} from "./models/_uporabnik";
import {_Tocke} from "./models/_tocke";

@Component({
    moduleId: module.id,
    selector: 'dodaj-kartico',
    templateUrl: 'kartica-dodaj.component.html'
})
export class KarticaDodajComponent {
    kartica: _Tocke = new _Tocke;
    uporabnik: Uporabnik;
    ponudniki: Ponudnik[];

    constructor(private tockeService: TockeService,
                private route: ActivatedRoute,
                private router: Router,
                private uporabnikService: UporabnikService,
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
        u.id = this.uporabnik.id;
        u.email = this.uporabnik.email;
        let t = new _Tocke();
        t.zbrane_tocke = this.kartica.zbrane_tocke;
        t.ponudnik_id = this.kartica.ponudnik_id;
        t.uporabnik = u;
        this.tockeService
            .create(t)
            .then(() => {
                this.router.navigate(['/uporabniki']);
            });
    }

    nazaj(): void {
        this.router.navigate(['/uporabniki']);
    }

    getPonudniki(): Promise<Ponudnik[]> {
        return this.http.get('http://ponudniki.herokuapp.com/v1/ponudniki')
            .toPromise()
            .then(response => response as Uporabnik[])
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}
