
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

@Path("tocke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TockeVir {

    private Client httpClient;

    @Inject
    private TockeZrno tBean;

    @Context
    protected UriInfo uriInfo;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        String baseUrl = "http://192.168.99.100:8081/v1";
    }

    public List<Tocke_ponudnik> vrniTockeSPonudniki(List<Tocke> tocke){
        List<Tocke_ponudnik> tp = new LinkedList<>();
        for(int i = 0; i < tocke.size(); i++){
            Tocke t = tocke.get(i);
            Ponudnik p = httpClient
                    .target("https://ponudniki.herokuapp.com/v1/ponudniki" + t.getPonudnik_id())
                    .request()
                    .get(new GenericType<Ponudnik>(){});
            Tocke_ponudnik TP = new Tocke_ponudnik();
            TP.setId_kartice(t.getId_kartice());
            TP.setZbrane_tocke(t.getZbrane_tocke());
            TP.setUporabnik(t.getUporabnik());
            TP.setPonudnik(p);
            tp.add(TP);
        }
        return tp;
    }

    @GET
    public Response vrniTocke(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Tocke> tocke = tBean.pridobiTocke(query);
        List<Tocke_ponudnik> tp = vrniTockeSPonudniki(tocke);
        return Response.status(Response.Status.OK).entity(tp).build();

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
