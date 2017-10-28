package si.fri.prpo.skupina20;
        import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.*;
        import java.io.*;
        import java.util.Optional;
        import java.util.logging.Logger;


@WebServlet("servlet")
public class JdbcServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Logger LOGGER = Logger.getLogger("InfoLogging");
        // implementacija 1. naloga
        PrintWriter writer = resp.getWriter();
        writer.append("Hello World\n");
        LOGGER.info("Hello World\n");
        String serviceName = ConfigurationUtil.getInstance().get("kumuluzee.name").orElse("VReDNOSTI NI");
        writer.append(serviceName);

        // implementacija 2. naloga
        UporabnikDAOimpl xy = new UporabnikDAOimpl();
        Uporabnik up = new Uporabnik();
        Uporabnik up2 = new Uporabnik();
        // INSERT
        up.setIme("Kr");
        up.setPriimek("Neki");
        up.setUporabniskoIme("krneki123");
        up.setEmail("kr.nekiAFNAznjmajl.si");
        up.setId(1);
        up2.setIme("Michael");
        up2.setPriimek("Neki");
        up2.setUporabniskoIme("mike");
        up2.setEmail("232323mikemikeAFNAznjmejl.si");
        up2.setId(2);
        xy.vstavi(up);
        xy.vstavi(up2);
        // SELECT
        writer.append(xy.vrniVse().toString());
        // writer.append(xy.vrni(1).toString());
        LOGGER.info(xy.vrniVse().toString());
        // LOGGER.info(xy.vrni(1).toString());
        /*
        // UPDATE
        up.setEmail("krnekiAFNAhotmejl.si");
        xy.posodobi(up);
        writer.append(xy.vrni(1).toString());
        LOGGER.info(xy.vrni(1).toString());
        // DELETE
        xy.odstrani(1);
        writer.append(xy.vrniVse().toString());
        LOGGER.info(xy.vrniVse().toString());
        */
    }
}
