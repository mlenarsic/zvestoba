
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("ponudniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PonudnikiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private QueueManager qm;

    ArrayList<Ponudnik> ponudniki;

    private Logger log = Logger.getLogger(PonudnikiVir.class.getName());

    private void init() {
        if(ponudniki == null) {
            ponudniki = new ArrayList<Ponudnik>();

            Ponudnik p1 = new Ponudnik();
            Ponudnik p2 = new Ponudnik();

            p1.setId(1);
            p1.setIme("Mercator");

            p2.setId(2);
            p2.setIme("Hofer");

            ponudniki.add(p1);
            ponudniki.add(p2);
        }
    }

    @GET
    public Response vrniPonudike(){
       init();
        return Response.status(Response.Status.OK).entity(ponudniki).build();
    }

    @Path("{id}")
    @GET
    public Response vrniPonudnika(@PathParam("id") Integer id) {
       init();
        for (int i = 0; i < ponudniki.size(); i++) {
            if (ponudniki.get(i).getId() == id) {
                return Response.status(Response.Status.OK).entity(ponudniki.get(i)).build();
            }
        }
        return null;
    }

    @POST
    public Response dodajPonudnika(Ponudnik ponudnik) {
        init();
        ponudniki.add(ponudnik);
        qm.posljiObvestiloODodanemPonudniku(ponudnik.getId());
        return Response.status(Response.Status.CREATED).entity(ponudnik).build();
    }

}
