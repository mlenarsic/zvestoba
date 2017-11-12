import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@BeleziKlice
public class BelezenjeKlicevZrno {
    private int stKlicev = 0;

    public void izvedenKlic() {
        stKlicev++;
        System.out.println(stKlicev);
    }
}
