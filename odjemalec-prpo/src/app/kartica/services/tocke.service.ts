import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

import {Tocke} from '../models/tocke';
import {_Tocke} from "../models/_tocke";

@Injectable()
export class TockeService {

    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    private url = 'http://localhost:8080/v1/tocke';

    constructor(private http: HttpClient) {
    }

    getTocke(): Promise<Tocke[]> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response as Tocke[])
            .catch(this.handleError);
    }

    getTocka(id: number): Promise<Tocke> {
        const url = `${this.url}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response as Tocke)
            .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${this.url}/${id}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null, () => null)
            .catch(this.handleError);
    }

    create(tocke: _Tocke): Promise<void> {
        return this.http
            .post(this.url, JSON.stringify(tocke), {headers: this.headers})
            .toPromise()
            .then()
            .catch(this.handleError);
    }

    update(tocke: _Tocke): Promise<void> {
        const url = `${this.url}/${tocke.id_kartice}`
        console.log(url);
        return this.http
            .put(url, JSON.stringify(tocke), {headers: this.headers})
            .toPromise()
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}

