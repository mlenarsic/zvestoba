import javax.persistence.*;

@Entity(name = "tocke")
@NamedQueries(value =
        {
                @NamedQuery(name = "Tocke.getAll", query = "SELECT t FROM tocke t"),
                @NamedQuery(name = "Tocke.getByPonudnik",
                        query = "SELECT t FROM tocke t where t.ponudnik_id = :pid"),
                @NamedQuery(name = "Tocke.getByUporabnik",
                        query = "SELECT t FROM tocke t where t.uporabnik = :uporabnik "),
                @NamedQuery(name = "Tocke.getTopTockeByPonudnik",
                        query = "SELECT t FROM tocke t where t.ponudnik_id = :pid " +
                                "and t.zbrane_tocke > :tocke order by t.zbrane_tocke"),
                @NamedQuery(name = "Tocke.getById",
                        query = "SELECT t FROM tocke t where t.id_kartice = :id "),
                @NamedQuery(name = "Tocke.getByUporabnikAndPonudnikId",
                        query = "SELECT t FROM tocke t where t.ponudnik_id = :pid " +
                            "and t.uporabnik = :u")
        })
public class Tocke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_kartice;

    private Integer ponudnik_id;

    private Integer zbrane_tocke;

    @ManyToOne
    @JoinColumn(name = "id")
    private Uporabnik uporabnik;

    // getter in setter metode
    public Integer getId_kartice() {
        return id_kartice;
    }

    public void setId_kartice(Integer id_kartice) {
        this.id_kartice = id_kartice;
    }

    public Integer getPonudnik_id() {
        return ponudnik_id;
    }

    public void setPonudnik_id(Integer ponudnik_id) {
        this.ponudnik_id = ponudnik_id;
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

    public String tostring() {
        return "TOCKA: " + "ID:" + id_kartice.toString() + ", PID:" + ponudnik_id.toString() + ", UPORABNIK:" + uporabnik.tostring() + ", STEVILO TOCK:" + zbrane_tocke.toString();
    }
}