package classes;

import javax.persistence.*;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "classes.Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name="classes.Uporabnik.getByIme",
                        query="SELECT u FROM uporabnik u WHERE u.ime = :name"),
                @NamedQuery(name="classes.Uporabnik.getById",
                        query="SELECT u FROM uporabnik u WHERE u.id = :id"),
                @NamedQuery(name="classes.Uporabnik.getNew",
                        query="SELECT u FROM uporabnik u WHERE u.id > :id")

        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uporabnik_id_seq")
    private Integer id;

    private String ime;

    private String priimek;

    private String uporabnisko_ime;

    private String email;

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

    public String tostring() {
        return "UPORABNIK:" + getIme() + " " + getPriimek() + " " + getUporabnisko_ime() + "  " + getEmail() + " " + getId();
    }
}