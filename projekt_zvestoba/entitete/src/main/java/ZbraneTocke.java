import javax.persistence.*;

@Entity(name = "zbrane_tocke")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM opomnik o")
        })
public class ZbraneTocke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int stevilo_tock;

    private int ponudnik;

    private int uporabnik;

    @ManyToOne
    @JoinColumn(name = "id")
    private Lokacija lokacija;

    // getter in setter metode

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStevilo_tock() {
        return stevilo_tock;
    }

    public void setStevilo_tock(int stevilo_tock) {
        this.stevilo_tock = stevilo_tock;
    }

    public int getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(int ponudnik) {
        this.ponudnik = ponudnik;
    }

    public int getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(int uporabnik) {
        this.uporabnik = uporabnik;
    }
}