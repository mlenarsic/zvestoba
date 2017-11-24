import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeZvestobeZrno {


    @Inject
    private UporabnikiZrno uz;

    @Inject
    private TockeZrno tz;

    @Inject
    private StoritveZrno sz;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializirano");
    }

    public void dodajTockeZaOpravljenoStoritev(Storitev s){
        Uporabnik u = s.getUporabnik();
        Tocke t = tz.pridobiTocko(u,s.getPonudnikId());
        if(t == null){
            t = new Tocke();
            t.setZbrane_tocke(s.getTocke());
            t.setUporabnik(s.getUporabnik());
            t.setPonudnik_id(s.getPonudnikId());
            tz.dodajTocko(t);
        } else {
            int stanje = t.getZbrane_tocke();
            stanje += s.getTocke();
            t.setZbrane_tocke(stanje);
            tz.posodobiTocko(t.getId_kartice(), t);
        }
    }

    public void koristiTocke(int idUp, int pid){
        Uporabnik u = uz.pridobiUporabnika(idUp);
        Tocke t = tz.pridobiTocko(u,pid);
        if(t == null){
            log.info("Uporabnik pri temu ponudniku nima kartice!");
        } else {
            int stanje = t.getZbrane_tocke();
            stanje -= 10;
            if (stanje < 0) {
                log.info("Premalo tock!");
            } else {
                t.setZbrane_tocke(stanje);
                tz.posodobiTocko(t.getId_kartice(), t);
            }
        }
    }

}
