import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("upravljanjeZvestobe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UpravljanjeZvestobeVir {
    @Inject
    private UpravljanjeZvestobeZrno uzBean;

    @Path("{id}")
    @PUT
    public Response opravljenaStoritev(@PathParam("id") Integer id, Storitev storitev) {

        uzBean.dodajTockeZaOpravljenoStoritev(storitev);
        return Response.status(Response.Status.OK).build();

    }
    /*
    @Path("{id}")
    @PUT
    public Response koristenjeTock(@PathParam("id") Integer id, Uporabnik uporabnik, Integer stevilo) {
        uzBean.koristiTocke(uporabnik, id, stevilo);
        return Response.status(Response.Status.OK).build();
    }
    */
}
