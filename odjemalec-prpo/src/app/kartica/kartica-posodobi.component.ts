import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

import {Tocke} from './models/tocke';
import {TockeService} from './services/tocke.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import {_Tocke} from "./models/_tocke";


@Component({
    moduleId: module.id,
    selector: 'posodobi-kartico',
    templateUrl: 'kartica-posodobi.component.html'
})
export class KarticaPosodobiComponent {
    kartica: Tocke = new Tocke;

    constructor(private tockeService: TockeService,
                private route: ActivatedRoute,
                private router: Router,
                private location: Location,
                private http: HttpClient
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.tockeService.getTocka(+params['id']))
            .subscribe(tocke => this.kartica = tocke);
    }
    submitForm(): void {
        let t = new _Tocke();
        t.zbrane_tocke = this.kartica.zbrane_tocke;
        t.uporabnik = this.kartica.uporabnik;
        t.ponudnik_id = this.kartica.ponudnik.id;
        t.id_kartice = this.kartica.id_kartice;
        this.tockeService
            .update(t);
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
