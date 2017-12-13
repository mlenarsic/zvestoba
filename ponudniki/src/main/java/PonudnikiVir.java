
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.logging.Logger;

@Path("ponudniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PonudnikiVir {

    @Inject
    private PonudnikiZrno uBean;

    @Context
    protected UriInfo uriInfo;

    private Logger log = Logger.getLogger(PonudnikiZrno.class.getName());

    @GET
    public Response vrniPonudike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Ponudnik> ponudniki = uBean.pridobiPonudnike(query);
        return Response.status(Response.Status.OK).entity(ponudniki).build();
    }

    @Path("{id}")
    @GET
    public Response vrniPonudnika(@PathParam("id") Integer id) {
        Ponudnik ponudnik = uBean.pridobiPonudnika(id);
        return Response.status(Response.Status.OK).entity(ponudnik).build();
    }

}
