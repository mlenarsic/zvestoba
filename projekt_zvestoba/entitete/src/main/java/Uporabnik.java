import javax.persistence.*;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM uporabnik o")
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String priimek;

    private String uporabnisko_ime;

    private String email;

    @ManyToOne
    @JoinColumn(name = "lokacija_id")
    private Storitev storitev;

    // getter in setter metode


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

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}