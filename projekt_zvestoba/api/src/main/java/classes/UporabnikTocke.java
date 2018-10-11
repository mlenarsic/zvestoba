package classes;
import java.util.List;

public class UporabnikTocke {
    private Integer id;

    private String ime;

    private String priimek;

    private String uporabnisko_ime;

    private String email;

    private List<Tocke_ponudnik> tocke;

    // getter in setter metode
    public List<Tocke_ponudnik> getTocke(){return this.tocke;}

    public void setTocke(List<Tocke_ponudnik> t){this.tocke = t;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabnisko_ime() {
        return uporabnisko_ime;
    }

    public void setUporabnisko_ime(String uporabnisko_ime) {
        this.uporabnisko_ime = uporabnisko_ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String tostring() {
        return "UPORABNIK:" + getIme() + " " + getPriimek() + " " + getUporabnisko_ime() + "  " + getEmail() + " " + getId();
    }
}