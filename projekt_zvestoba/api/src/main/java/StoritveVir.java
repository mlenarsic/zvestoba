
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;
import java.util.LinkedList;
import java.util.List;

@Path("storitve")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StoritveVir {

    @Inject
    private StoritveZrno sBean;

    @Context
    protected UriInfo uriInfo;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public Storitve_ponudnik vrniStoritevSPonudnikom(Storitev s){
        Ponudnik p =  httpClient
                .target("http://ponudniki.herokuapp.com/v1/ponudniki/" + s.getPonudnikId())
                .request()
                .get(new GenericType<Ponudnik>(){});
        Storitve_ponudnik SP = new Storitve_ponudnik();
        SP.setNaziv(s.getNaziv());
        SP.setOpis(s.getOpis());
        SP.setStoritevId(s.getStoritevId());
        SP.setTocke(s.getTocke());
        SP.setUporabnik(s.getUporabnik());
        SP.setPonudnik(p);
        return SP;
    }

    public List<Storitve_ponudnik> vrniStoritveSPonudniki(List<Storitev> storitve){
        List<Storitve_ponudnik> sp = new LinkedList<>();
        for(int i = 0; i < storitve.size(); i++){
            sp.add(vrniStoritevSPonudnikom(storitve.get(i)));
        }
        return sp;
    }

    @GET
    public Response vrniStoritve(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Storitev> storitve = sBean.pridobiStoritve(query);
        List<Storitve_ponudnik> s = vrniStoritveSPonudniki(storitve);
        return Response.status(Response.Status.OK).entity(s).build();

    }

    @Path("{id}")
    @GET
    public Response vrniStoritev(@PathParam("id") Integer id) {
        Storitev storitev = sBean.pridobiStoritev(id);
        Storitve_ponudnik s = vrniStoritevSPonudnikom(storitev);
        return Response.status(Response.Status.OK).entity(s).build();

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
