package klasi;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.jetty.http2.api.Session;

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

    @Operation(description = "Vrne seznam tock.", summary = "Seznam tock", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam tock",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    @GET
    public Response vrniTocke(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Tocke> tocke = tBean.pridobiTocke(query);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Operation(description = "Vrne tocko z dolocenim id-jem.", summary = "Tocka", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})

    @Path("{id}")
    @GET
    public Response vrniTocke(@PathParam("id") Integer id) {

        Tocke tocke = tBean.pridobiTocko(id);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Operation(description = "Doda tocko v bazo.", summary = "Dodajanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila dodana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    @POST
    public Response dodajTocke(Tocke tocke) {

        tBean.dodajTocko(tocke);
        return Response.status(Response.Status.CREATED).entity(tocke).build();

    }

    @Operation(description = "Posodoboi tocko z dolocenim id-jem.", summary = "Posodabljanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila posodoboljena",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    @Path("{id}")
    @PUT
    public Response posodobiPocke(@PathParam("id") Integer id, Tocke tocke) {

        tBean.posodobiTocko(id, tocke);
        return Response.status(Response.Status.OK).entity(tocke).build();

    }

    @Operation(description = "Izbrise tocko z dolocenim id-jem iz baze.", summary = "Brisanje tocke", tags = "tocke", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocka je bila izbrisana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    @Path("{id}")
    @DELETE
    public Response izbrisiTocke(@PathParam("id") Integer id) {

        tBean.odstraniTocko(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
