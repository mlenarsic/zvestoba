import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {UporabnikiComponent} from './uporabnik/uporabniki.component';
import {UporabnikiDodajComponent} from './uporabnik/uporabniki-dodaj.component';
import {UporabnikPodrobnostiComponent} from './uporabnik/uporabnik-podrobnosti.component';
import {UporabnikService} from './uporabnik/services/uporabnik.service';
import {KarticaDodajComponent} from "./kartica/kartica-dodaj.component";
import {TockeService} from "./kartica/services/tocke.service";
import {KarticaPosodobiComponent} from "./kartica/kartica-posodobi.component";
import {UporabnikPosodobiComponent} from "./uporabnik/uporabnik-posodobi.component";


@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        UporabnikiComponent,
        UporabnikPodrobnostiComponent,
        UporabnikiDodajComponent,
        KarticaDodajComponent,
        KarticaPosodobiComponent,
        UporabnikPosodobiComponent
    ],
    providers: [UporabnikService, TockeService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

