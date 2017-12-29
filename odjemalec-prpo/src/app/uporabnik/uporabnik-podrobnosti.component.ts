import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

import {Uporabnik} from './models/uporabnik';
import {UporabnikService} from './services/uporabnik.service';
import {TockeService} from "./services/tocke.service";
import {Tocke} from "./models/tocke";

@Component({
    moduleId: module.id,
    selector: 'uporabnik-podrobnosti',
    templateUrl: 'uporabnik-podrobnosti.component.html'
})
export class UporabnikPodrobnostiComponent implements OnInit {
    uporabnik: Uporabnik;

    constructor(private uporabnikService: UporabnikService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router,
                private tockeService: TockeService
               ) {
    }

    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.uporabnikService.getUporabnik(+params['id']))
            .subscribe(uporabnik => this.uporabnik = uporabnik);

    }

    nazaj(): void {
        this.location.back();
    }

    dodajKartico(uporabnik: Uporabnik): void {
        this.router.navigate(['/dodajkartico/', uporabnik.id]);
    }

    delete(tocke: Tocke): void {
        this.tockeService
            .delete(tocke.id_kartice)
            .then(() => {
                this.uporabnik.tocke = this.uporabnik.tocke.filter(t => t !== tocke);
                for (let tocka of this.uporabnik.tocke){
                    if (tocka === tocke) {
                        tocka = null;
                    }
                }
            });
    }
}
