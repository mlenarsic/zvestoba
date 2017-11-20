
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.logging.Logger;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    @Inject
    private UporabnikiZrno uBean;

    @Inject
    private IdentifikatorZrno iz;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @GET
    public Response vrniUporabnike(){
        log.info("Zacetek: vrni uporabnike");
        log.info("Generiran identifikator:" + iz.getIdentifikator());
        List<Uporabnik> uporabniki  = uBean.pridobiUporabnike();
        log.info("Konec: vrni uporabnike");
        log.info("Generiran identifikator:" + iz.getIdentifikator());

        return Response.status(Response.Status.OK).entity(uporabniki).build();

    }

    @Path("{id}")
    @GET
    public Response vrniUporabnika(@PathParam("id") Integer id) {


        Uporabnik uporabnik = uBean.pridobiUporabnika(id);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }

    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {

        uBean.dodajUporabnika(uporabnik);
        return Response.status(Response.Status.CREATED).entity(uporabnik).build();

    }

    @Path("{id}")
    @PUT
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {

        uBean.posodobiUporabnika(id, uporabnik);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }

    @Path("{id}")
    @DELETE
    public Response izbrisiUporabnika(@PathParam("id") Integer id) {

        uBean.odstraniUporabnika(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}