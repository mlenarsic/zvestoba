import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

import {Uporabnik} from '../models/uporabnik';
import {_Uporabnik} from "../models/_uporabnik";

@Injectable()
export class UporabnikService {

    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    private url = 'http://localhost:8080/v1/uporabniki';

    constructor(private http: HttpClient) {
    }

    getUporabniki(): Promise<Uporabnik[]> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response as Uporabnik[])
            .catch(this.handleError);
    }

    getUporabnik(id: number): Promise<Uporabnik> {
        const url = `${this.url}/${id}/tocke`;
        return this.http.get(url)
            .toPromise()
            .then(response => response as Uporabnik)
            .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${this.url}/${id}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null, () => null)
            .catch(this.handleError);
    }

    update(uporabnik: _Uporabnik): Promise<void> {
        const url = `${this.url}/${uporabnik.id}`
        console.log(url);
        return this.http
            .put(url, JSON.stringify(uporabnik), {headers: this.headers})
            .toPromise()
            .catch(this.handleError);
    }

    create(uporabnik: Uporabnik): Promise<void> {
        return this.http
            .post(this.url, JSON.stringify(uporabnik), {headers: this.headers})
            .toPromise()
            .then()
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}

