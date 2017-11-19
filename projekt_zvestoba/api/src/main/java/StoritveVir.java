
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("storitve")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StoritveVir {

    @Inject
    private StoritveZrno sBean;

    @GET
    public Response vrniStoritve(){

        List<Storitev> storitve  = sBean.pridobiStoritve();
        return Response.status(Response.Status.OK).entity(storitve).build();

    }

    @Path("{id}")
    @GET
    public Response vrniStoritev(@PathParam("id") Integer id) {

        Storitev storitev = sBean.pridobiStoritev(id);
        return Response.status(Response.Status.OK).entity(storitev).build();

    }

    @POST
    public Response dodajStoritev(Storitev storitev) {

        sBean.dodajStoritev(storitev);
        return Response.status(Response.Status.CREATED).entity(storitev).build();

    }

    @Path("{id}")
    @PUT
    public Response posodobiStoritev(@PathParam("id") Integer id, Storitev storitev) {

        sBean.posodobiStoritev(id, storitev);
        return Response.status(Response.Status.OK).entity(storitev).build();

    }

    @Path("{id}")
    @DELETE
    public Response izbrisiStoritev(@PathParam("id") Integer id) {

        sBean.odstraniStoritev(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
