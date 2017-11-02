import javax.persistence.*;

@Entity(name = "storitev")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM opomnik o")
        })
public class Storitev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storitevId;

    private String naslov;

    private String opis;

    @ManyToOne
    @JoinColumn(name = "naziv")
    private Uporabnik uporabnik;

    // getter in setter metode

}