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
                .target("http://localhost:8081/v1/ponudniki/" + s.getPonudnikId())
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

    @Operation(description = "Vrne seznam storitev iz baze.", summary = "Seznam storitev", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam storitev",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Storitev.class))
            )})
    @GET
    public Response vrniStoritve(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Storitev> storitve = sBean.pridobiStoritve(query);
        List<Storitve_ponudnik> s = vrniStoritveSPonudniki(storitve);
        return Response.status(Response.Status.OK).entity(s).build();

    }

    @Operation(description = "Vrne storitev z dolocenim id-jem iz baze.", summary = "Storitev  id-jem", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Storitev.class))
            )})
    @Path("{id}")
    @GET
    public Response vrniStoritev(@PathParam("id") Integer id) {
        Storitev storitev = sBean.pridobiStoritev(id);
        Storitve_ponudnik s = vrniStoritevSPonudnikom(storitev);
        return Response.status(Response.Status.OK).entity(s).build();

    }

    @Operation(description = "Doda storitev v bazo.", summary = "Dodajanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila dodana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Storitev.class))
            )})
    @POST
    public Response dodajStoritev(Storitev storitev) {

        sBean.dodajStoritev(storitev);
        return Response.status(Response.Status.CREATED).entity(storitev).build();

    }

    @Operation(description = "Posodobi storitev z dolocenim id-jem v bazi.", summary = "Posodabljanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila posodobljena",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Storitev.class))
            )})
    @Path("{id}")
    @PUT
    public Response posodobiStoritev(@PathParam("id") Integer id, Storitev storitev) {

        sBean.posodobiStoritev(id, storitev);
        return Response.status(Response.Status.OK).entity(storitev).build();

    }

    @Operation(description = "Izbrise storitev z dolocenim id-jem iz baze.", summary = "Brisanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila izbrisana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Storitev.class))
            )})
    @Path("{id}")
    @DELETE
    public Response izbrisiStoritev(@PathParam("id") Integer id) {

        sBean.odstraniStoritev(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
