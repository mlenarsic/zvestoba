
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("tocke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TockeVir {

    @Inject
    private TockeZrno tBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response vrniTocke(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Tocke> tocke = tBean.pridobiTocke(query);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Path("{id}")
    @GET
    public Response vrniTocke(@PathParam("id") Integer id) {

        Tocke tocke = tBean.pridobiTocko(id);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @POST
    public Response dodajTocke(Tocke tocke) {

        tBean.dodajTocko(tocke);
        return Response.status(Response.Status.CREATED).entity(tocke).build();

    }

    @Path("{id}")
    @PUT
    public Response posodobiPocke(@PathParam("id") Integer id, Tocke tocke) {

        tBean.posodobiTocko(id, tocke);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Path("{id}")
    @DELETE
    public Response izbrisiTocke(@PathParam("id") Integer id) {

        tBean.odstraniTocko(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
