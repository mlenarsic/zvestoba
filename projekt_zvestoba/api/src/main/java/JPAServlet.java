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
    private UpravljanjeZvestobeZrno uzz;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {

        PrintWriter writer = resp.getWriter();
        printUporabniki(writer);
        printStoritve(writer);
        printTocke(writer);
        writer.append("\n");

        Uporabnik u = uzz.dodajUporabnika("Mama","Pujsa", "mama@pujsa.oink", "mama_pujsa");
        uzz.dodajStoritev("Nakup brisac", "Stranka je kupila nase brisace", 1,10,u);
        printUporabniki(writer);
        printStoritve(writer);
        printTocke(writer);
        writer.append("\n");


        uzz.koristiTocke(u,1,7);
        printUporabniki(writer);
        printStoritve(writer);
        printTocke(writer);
        writer.append("\n");


    }

    public void printUporabniki(PrintWriter writer){
        List<Uporabnik> u = uzz.vrniUporabnike();
        for(int i = 0; i < u.size(); i++){
            writer.append(u.get(i).tostring() + "\n");
        }
    }

    public void printStoritve(PrintWriter writer){
        List<Storitev> s = uzz.vrniStoritve();
        for(int i = 0; i < s.size(); i++){
            writer.append(s.get(i).tostring() + "\n");
        }
    }

    public void printTocke(PrintWriter writer){
        List<Tocke> t = uzz.vrniTocke();
        for(int i = 0; i < t.size(); i++){
            writer.append(t.get(i).tostring() + "\n");
        }
    }

}
