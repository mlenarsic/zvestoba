import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @Inject
    private TockeZrno tockeZrno;

    @Inject
    private StoritveZrno storitveZrno;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {

        PrintWriter writer = resp.getWriter();
        testUporabniki(writer);
        writer.append("\n" + "KONEC TESTA UPORABNIKI" + "\n\n");
        testTocke(writer);
        writer.append("KONEC TESTA TOCKE" + "\n\n");
        testStoritve(writer);
        writer.append("KONEC TESTA STORITVE" + "\n\n");

    }

    public void testStoritve(PrintWriter writer){
        //sprintaj vse tocke
        printStoritve(writer);
        writer.append("\n");

        //dodaj novo storitev nekemu uporabniku
        Uporabnik u = uporabnikiZrno.pridobiUporabnika(3);
        String opis = "Zgubil je proti Mayweatherju 26. augusta";
        String naziv = "Zguba";
        dodajNovoStoritev(opis, naziv,1,1000000000,u);
        printStoritve(writer);
        writer.append("\n");

        //sprintaj samo storitev z id-jem 3
        printStoritevWithId(3,writer);
        writer.append("\n");

        //posodobi storitev z id-jem 3
        Storitev s = new Storitev();
        u = uporabnikiZrno.pridobiUporabnika(3);
        s.setTocke(1000000);
        s.setUporabnik(u);
        s.setPonudnikId(3);
        s.setNaziv("Lezi");
        s.setOpis("Lezi na travniku na in se pogovarja s svizcem");
        storitveZrno.posodobiStoritev(3, s);
        printStoritevWithId(3,writer);
        writer.append("\n");


        //še enkrat sprintaj vse storitve
        printStoritve(writer);
        writer.append("\n");
    }


    public void printStoritve(PrintWriter writer){
        List<Storitev> storitve = storitveZrno.pridobiStoritve();
        for(int i = 0; i < storitve.size(); i++) {
            Storitev s = storitve.get(i);
            writer.append(s.tostring());
            writer.append("\n");
        }
    }

    public void printStoritevWithId(int id, PrintWriter writer){
        Storitev s = storitveZrno.pridobiStoritev(id);
        writer.append(s.tostring());
        writer.append("\n");

    }

    public void dodajNovoStoritev(String opis, String naziv, int ponudnik_id, int tocke, Uporabnik u ){
        Storitev s = new Storitev();
        s.setOpis(opis);
        s.setNaziv(naziv);
        s.setPonudnikId(ponudnik_id);
        s.setUporabnik(u);
        s.setTocke(tocke);
        storitveZrno.dodajStoritev(s);

    }


    public void testTocke(PrintWriter writer){
        //sprintaj vse tocke
        printTocke(writer);
        writer.append("\n");

        //dodaj nove tocke v smislu da se doda nova kartica in potem sprintaj vse tocke
        Uporabnik u = uporabnikiZrno.pridobiUporabnika(3);
        dodajNovoTocko(3,2,u);
        printTocke(writer);
        writer.append("\n");

        //sprintaj samo tocke z id-jem 3
        printTockeWithId(3,writer);
        writer.append("\n");

        //posodobi tocke z id-jem 3
        Tocke t = new Tocke();
        u = uporabnikiZrno.pridobiUporabnika(2);
        t.setPonudnik_id(7);
        t.setUporabnik(u);
        t.setZbrane_tocke(100);
        tockeZrno.posodobiTocko(3,t);
        printTockeWithId(3,writer);
        writer.append("\n");

        //še enkrat sprintaj vse tocke
        printTocke(writer);
        writer.append("\n");


    }

    public void printTocke(PrintWriter writer){
        List<Tocke> tocke = tockeZrno.pridobiTocke();
        for(int i = 0; i < tocke.size(); i++) {
            Tocke t = tocke.get(i);
            writer.append(t.tostring());
            writer.append("\n");
        }
    }

    public void printTockeWithId(int id, PrintWriter writer) {
        Tocke t = tockeZrno.pridobiTocko(id);
        writer.append(t.tostring());
        writer.append("\n");
    }

    public void dodajNovoTocko(int ponudnik_id, int zbrane_tocke, Uporabnik u){
        Tocke t = new Tocke();
        t.setZbrane_tocke(zbrane_tocke);
        t.setUporabnik(u);
        t.setPonudnik_id(ponudnik_id);
        tockeZrno.dodajTocko(t);
    }


    public void testUporabniki(PrintWriter writer){
        //sprintaj vse uporabnike
        printUporabniki(writer);
        writer.append("\n");

        //dodaj novega uporabnika v bazo in potem sprintaj vse uporabnike
        dodajNovegaUporabnika("Conor","McGregor","gmail@conormcgregor.com","notorious");
        printUporabniki(writer);
        writer.append("\n");

        //sprintaj samo uporabnika z id-jem 3
        printUporabnikWithId(3,writer);
        writer.append("\n");

        //posodobi uporabnika z id-jem 3 na 4 in sprintaj uporabnika z id-jem 4
        Uporabnik u = new Uporabnik();
        u.setIme("Krava");
        u.setPriimek("Milka");
        u.setEmail("krava@milka.mu");
        u.setUporabnisko_ime("krava_milka");
        uporabnikiZrno.posodobiUporabnika(3,u);
        printUporabnikWithId(3,writer);
        writer.append("\n");

        //še enkrat sprintaj vse uporabnike
        printUporabniki(writer);
    }

    public void printUporabniki(PrintWriter writer){
        List<Uporabnik> uporabniki = uporabnikiZrno.pridobiUporabnike();
        for(int i = 0; i < uporabniki.size(); i++) {
            Uporabnik u = uporabniki.get(i);
            writer.append(u.tostring());
            writer.append("\n");
        }
    }
    public void printUporabnikWithId(int id, PrintWriter writer) {

        Uporabnik u = uporabnikiZrno.pridobiUporabnika(id);
        writer.append(u.tostring());
        writer.append("\n");

    }

    public void dodajNovegaUporabnika(String ime, String priimek, String email, String username){
        Uporabnik u = new Uporabnik();
        u.setIme(ime);
        u.setPriimek(priimek);
        u.setUporabnisko_ime(username);
        u.setEmail(email);
        uporabnikiZrno.dodajUporabnika(u);
    }
}
