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


    public Uporabnik dodajUporabnika(String ime, String priimek, String email, String ui){
        Uporabnik u = new Uporabnik();
        u.setEmail(email);
        u.setPriimek(priimek);
        u.setIme(ime);
        u.setUporabnisko_ime(ui);
        uz.dodajUporabnika(u);
        return u;
    }

    public Tocke dodajTocko(int st, int pid, Uporabnik u){
        Tocke t = new Tocke();
        t.setPonudnik_id(pid);
        t.setUporabnik(u);
        t.setZbrane_tocke(st);
        tz.dodajTocko(t);
        return t;
    }

    public Storitev dodajStoritev(String naziv, String opis, int pid, int tocke, Uporabnik u){
        Storitev s = new Storitev();
        s.setTocke(tocke);
        s.setPonudnikId(pid);
        s.setNaziv(naziv);
        s.setOpis(opis);
        s.setUporabnik(u);
        sz.dodajStoritev(s);
        dodajTockeZaOpravljenoStoritev(s);
        return s;
    }

    public void dodajTockeZaOpravljenoStoritev(Storitev s){
        Uporabnik u = s.getUporabnik();
        Tocke t = tz.pridobiTocko(u,s.getPonudnikId());
        if(t == null){
            dodajTocko(s.getTocke(), s.getPonudnikId(), u);
        } else {
            int stanje = t.getZbrane_tocke();
            stanje += s.getTocke();
            t.setZbrane_tocke(stanje);
            tz.posodobiTocko(t.getId_kartice(), t);
        }
    }

    public List<Uporabnik> vrniUporabnike(){
        return uz.pridobiUporabnike();
    }

    public List<Tocke> vrniTocke(){
        return tz.pridobiTocke();
    }

    public List<Storitev> vrniStoritve(){ return sz.pridobiStoritve(); }

    public void koristiTocke(Uporabnik u, int pid, int st){
        Tocke t = tz.pridobiTocko(u,pid);
        if(t == null){
            System.out.println("Ta kartica ne obstaja!!");
        } else {
            int stanje = t.getZbrane_tocke();
            stanje -= st;
            if (stanje < 0) {
                System.out.println("Premalo tock, koriscenje tock ni mogoce!");
            } else {
                t.setZbrane_tocke(stanje);
                tz.posodobiTocko(t.getId_kartice(), t);
            }
        }
    }

}
