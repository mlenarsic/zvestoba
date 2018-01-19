package classes;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    }
    public Tocke_ponudnik vrniTockoSPonudnikom(Tocke t){
        Ponudnik p =  httpClient
                .target("http://localhost:8081/v1/ponudniki/" + t.getPonudnik_id())
                .request()
                .get(new GenericType<Ponudnik>(){});
        Tocke_ponudnik TP = new Tocke_ponudnik();
        TP.setId_kartice(t.getId_kartice());
        TP.setZbrane_tocke(t.getZbrane_tocke());
        TP.setUporabnik(t.getUporabnik());
        TP.setPonudnik(p);
        return TP;


    }

    public List<Tocke_ponudnik> vrniTockeSPonudniki(List<Tocke> tocke){
        List<Tocke_ponudnik> tp = new LinkedList<>();
        for(int i = 0; i < tocke.size(); i++){
            tp.add(vrniTockoSPonudnikom(tocke.get(i)));
        }
        return tp;
    }

    @Operation(description = "Vrne seznam tock.", summary = "Seznam tock", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam tock",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @GET
    public Response vrniTocke(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Tocke> tocke = tBean.pridobiTocke(query);
        List<Tocke_ponudnik> tp = vrniTockeSPonudniki(tocke);
        return Response.status(Response.Status.OK).entity(tp).build();

    }

    @Operation(description = "Vrne tocko z dolocenim id-jem.", summary = "Tocka", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @Path("{id}")
    @GET
    public Response vrniTocke(@PathParam("id") Integer id) {

        Tocke tocke = tBean.pridobiTocko(id);
        Tocke_ponudnik tp = vrniTockoSPonudnikom(tocke);
        return Response.status(Response.Status.OK).entity(tp).build();

    }

    @Operation(description = "Doda tocko v bazo.", summary = "Dodajanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila dodana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @POST
    public Response dodajTocke(Tocke tocke) {
        System.out.println(tocke.getPonudnik_id());
        System.out.println(tocke.getZbrane_tocke());
        System.out.println(tocke.getUporabnik().tostring());
        tBean.dodajTocko(tocke);
        return Response.status(Response.Status.CREATED).entity(tocke).build();

    }

    @Operation(description = "Posodoboi tocko z dolocenim id-jem.", summary = "Posodabljanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila posodoboljena",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @Path("{id}")
    @PUT
    public Response posodobiPocke(@PathParam("id") Integer id, Tocke tocke) {
        System.out.println("\n\nPUT METODA\n\n");
        System.out.println(tocke.getId_kartice());
        System.out.println(tocke.getUporabnik().tostring());
        System.out.println(tocke.getZbrane_tocke());
        System.out.println(tocke.getPonudnik_id());
        tBean.posodobiTocko(id, tocke);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Operation(description = "Izbrise tocko z dolocenim id-jem iz baze.", summary = "Brisanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila izbrisana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @Path("{id}")
    @DELETE
    public Response izbrisiTocke(@PathParam("id") Integer id) {

        tBean.odstraniTocko(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
