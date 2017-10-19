package si.fri.prpo.skupina20;
        import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.*;
        import java.io.*;
        import java.util.Optional;


@WebServlet("servlet")
public class JdbcServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // implementacija
        PrintWriter writer = resp.getWriter();

        writer.append("Hello World\n");

        String serviceName = ConfigurationUtil.getInstance().get("kumuluzee.name").orElse("VReDNOSTI NI");
        writer.append(serviceName);

    }
}
