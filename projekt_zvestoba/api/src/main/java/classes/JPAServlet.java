package classes;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikiVir uv;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.append("Hello world\n");

        testPet(writer);

    }

    public void testPet (PrintWriter writer) {
        //classes.Uporabnik uporabnik = uv.vrniUporabnika(1).getEntity();

        // writer.append(uv.vrniUporabnike().getEntity() + "\n");
    }


}
