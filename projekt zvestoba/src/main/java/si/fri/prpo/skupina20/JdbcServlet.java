package si.fri.prpo.skupina20;
        import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.*;
        import java.io.*;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import java.util.logging.Logger;


@WebServlet("servlet")
public class JdbcServlet extends HttpServlet{
    Logger LOGGER = Logger.getLogger("InfoLogging");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        // implementacija 2. naloga
        UporabnikDAOimpl xy = new UporabnikDAOimpl();

        Uporabnik up = new Uporabnik();
        Uporabnik up2 = new Uporabnik();
        // INSERT -> dela!
        /*
        // up.setId(28);
        up.setIme("John");
        up.setPriimek("Smith");
        up.setUporabniskoIme("krneki123");
        up.setEmail("kr.nekiAFNAznjmajl.si");
        // up2.setId(29);
        up2.setIme("Michael");
        up2.setPriimek("Brown");
        up2.setUporabniskoIme("mike");
        up2.setEmail("232323mikemikeznjmejl.si");
        xy.vstavi(up);
        xy.vstavi(up2);
        */
        // SELECT -> dela!

        List<Entiteta> seznam = xy.vrniVse();
        for (int i = 0; i < seznam.size(); i++){
            Uporabnik temp = (Uporabnik)seznam.get(i);
            writer.append(temp.getIme()+ " " + temp.getPriimek() + " " + temp.getId() + "\n");
            LOGGER.info(temp.getIme()+ " " + temp.getPriimek() + "\n");
        }


        // KONEC DOMACE NALOGE

        // DELETE -> dela!
        /*
        xy.odstrani(20);
        xy.odstrani(21);
        xy.odstrani(22);
        xy.odstrani(23);
        Uporabnik uporabnik = (Uporabnik)xy.vrni(3);
        List<Entiteta> seznam = xy.vrniVse();
        for (int i = 0; i < seznam.size(); i++){
            Uporabnik temp = (Uporabnik)seznam.get(i);
            writer.append(temp.getIme()+"\n");
            LOGGER.info(temp.getIme()+"\n");
        }
        */
        // UPDATE -> dela!
        /*
        Uporabnik uporabnik = (Uporabnik)xy.vrni(1);
        uporabnik.setIme("Krava");
        //up.setId(22);
        //up2.setPriimek("Jordan");
        //up2.setId(17);
        xy.posodobi(uporabnik);
        //xy.posodobi(up2);
        List<Entiteta> seznam = xy.vrniVse();
        for (int i = 0; i < seznam.size(); i++){
            Uporabnik temp = (Uporabnik)seznam.get(i);
            writer.append(temp.getIme()+ " " + temp.getPriimek() + " " + temp.getId() + "\n");
            LOGGER.info(temp.getIme()+ " " + temp.getPriimek() + " " + temp.getId() + "\n");
        }
        */
    }
}
