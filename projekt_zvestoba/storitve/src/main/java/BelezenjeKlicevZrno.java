import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private int stKlicev = 0;

    public int izvedenKlic() {
        stKlicev++;
        return stKlicev;
    }
}
