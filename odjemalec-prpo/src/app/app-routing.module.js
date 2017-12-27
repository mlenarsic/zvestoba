"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var uporabniki_component_1 = require('./uporabniki.component');
var uporabnik_podrobnosti_component_1 = require('./uporabnik-podrobnosti.component');
var uporabniki_dodaj_component_1 = require('./uporabniki-dodaj.component');
var routes = [
    { path: '', redirectTo: '/uporabniki', pathMatch: 'full' },
    { path: 'uporabniki', component: uporabniki_component_1.UporabnikiComponent },
    { path: 'uporabniki/:id', component: uporabnik_podrobnosti_component_1.UporabnikPodrobnostiComponent },
    { path: 'dodajuporabnika', component: uporabniki_dodaj_component_1.UporabnikiDodajComponent },
    { path: '**', redirectTo: '/uporabniki', pathMatch: 'full' }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        core_1.NgModule({
            imports: [router_1.RouterModule.forRoot(routes)],
            exports: [router_1.RouterModule]
        }), 
        __metadata('design:paramtypes', [])
    ], AppRoutingModule);
    return AppRoutingModule;
}());
exports.AppRoutingModule = AppRoutingModule;
//# sourceMappingURL=app-routing.module.js.map