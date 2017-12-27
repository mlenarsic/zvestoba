public class Storitve_ponudnik {

    private Integer storitevId;

    private String naziv;

    private String opis;

    private Ponudnik ponudnik;

    private Integer tocke;

    private Uporabnik uporabnik;
    public Integer getStoritevId() {
        return storitevId;
    }

    public void setStoritevId(Integer storitevId) {
        this.storitevId = storitevId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Ponudnik getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(Ponudnik p) {
        this.ponudnik = p;
    }

    public int getTocke() {
        return tocke;
    }

    public void setTocke(int tocke) {
        this.tocke = tocke;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }


}
