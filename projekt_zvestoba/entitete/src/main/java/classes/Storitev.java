package classes;

import javax.persistence.*;

@Entity(name = "storitev")
@NamedQueries(value =
        {
                @NamedQuery(name = "classes.Storitev.getAll", query = "SELECT s FROM storitev s"),
                @NamedQuery(name = "classes.Storitev.getByNaziv",
                        query = "SELECT s FROM storitev s where s.naziv = :naziv"),
                @NamedQuery(name = "classes.Storitev.getByPonudnik",
                        query = "SELECT s FROM storitev s where s.ponudnikId = :pid"),
                @NamedQuery(name = "classes.Storitev.getTopStoritve",
                        query = "SELECT s FROM storitev s where s.tocke > :tocke ORDER BY s.tocke"),
                @NamedQuery(name = "classes.Storitev.getById",
                        query = "SELECT s FROM storitev s where s.storitevId = :id")
        })
public class Storitev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "storitev_storitevid_seq")
    private Integer storitevId;

    private String naziv;

    private String opis;

    private Integer ponudnikId;

    private Integer tocke;

    @ManyToOne
    @JoinColumn(name = "id")
    private Uporabnik uporabnik;

    // getter in setter metode

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

    public int getPonudnikId() {
        return ponudnikId;
    }

    public void setPonudnikId(int ponudnikId) {
        this.ponudnikId = ponudnikId;
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

    public String tostring() {
        return "STORITEV:" + "ID:" + storitevId.toString() + ", KAJ:" + naziv + ", KAKO:" + opis + " KDO:" + uporabnik.tostring() +
                ", KJE:" + getPonudnikId() + ", KOLIKO TOCK:" + getTocke();
    }
}