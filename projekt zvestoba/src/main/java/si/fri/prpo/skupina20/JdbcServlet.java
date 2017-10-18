package si.fri.prpo.skupina20;
import javax.servlet.http.HttpServlet;
import javax.annotitaion.webservlet;

@WebServlet("servlet")

public class JdbcServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // implementacija
        printWriter writer = resp.getWrtier();

        writer.append("Hello World\n");

        String serviceName = ConfigurationUtil.getInstance(),get("kumuluzee.name").orElse(other:"VREDNOSTI NI");
        writer.append(serviceName);

    }
}
