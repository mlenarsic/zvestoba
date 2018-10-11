package classes;

public class Tocke_ponudnik {

    private Integer id_kartice;

    private Integer zbrane_tocke;

    private Uporabnik uporabnik;

    private Ponudnik ponudnik;
    public Integer getId_kartice(){return id_kartice;}

    public void setId_kartice(Integer id_kartice) {
        this.id_kartice = id_kartice;
    }

    public Ponudnik getPonudnik() { return ponudnik; }

    public void setPonudnik(Ponudnik ponudnik) {
        this.ponudnik = ponudnik;
    }

    public Integer getZbrane_tocke() {
        return zbrane_tocke;
    }

    public void setZbrane_tocke(Integer zbrane_tocke) {
        this.zbrane_tocke = zbrane_tocke;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

}
