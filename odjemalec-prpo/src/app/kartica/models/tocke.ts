
import {Uporabnik} from "../../uporabnik/models/uporabnik";
import {Ponudnik} from "./ponudnik";
import {_Uporabnik} from "../../uporabnik/models/_uporabnik";

export class Tocke {
    id_kartice: number;
    zbrane_tocke: number;
    uporabnik: _Uporabnik;
    ponudnik: Ponudnik;
    // ostali atributi
}
