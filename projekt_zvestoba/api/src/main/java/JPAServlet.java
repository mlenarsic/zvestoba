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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {

        PrintWriter writer = resp.getWriter();

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();
        for(int i = 0; i < uporabniki.size(); i++){
            Uporabnik u = uporabniki.get(i);
            writer.append(u.getIme() + " " + u.getPriimek() + " " + u.getUporabnisko_ime() + " " + u.getId() + "\n");
        }


    }
}
