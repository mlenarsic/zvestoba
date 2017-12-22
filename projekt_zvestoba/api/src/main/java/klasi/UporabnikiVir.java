package klasi;

import com.kumuluz.ee.rest.beans.QueryParameters;
//import io.swagger.oas.annotations.Operation;
//import io.swagger.oas.annotations.headers.Header;
//import io.swagger.oas.annotations.media.Content;
//import io.swagger.oas.annotations.media.Schema;
//import io.swagger.oas.annotations.responses.ApiResponse;
//import io.swagger.oas.annotations.security.SecurityRequirement;
//import org.eclipse.jetty.http2.api.Session;

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

    @Context
    protected UriInfo uriInfo;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    /*
    @Operation(description = "Vrne seznam uporabnikov.", summary = "Seznam uporabnikov", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam uporabnikov",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uBean.pridobiUporabnike(query);
        return Response.status(Response.Status.OK).entity(uporabniki).build();
    }
    /*
    @Operation(description = "Vrne uporabnika z id-jem.", summary = "klasi.Uporabnik", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Uporabnik",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @GET
    public Response vrniUporabnika(@PathParam("id") Integer id) {


        Uporabnik uporabnik = uBean.pridobiUporabnika(id);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }
    /*
    @Operation(description = "Doda uporabnika v bazo.", summary = "Dodajanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Uporabnik je bil dodan",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {

        uBean.dodajUporabnika(uporabnik);
        return Response.status(Response.Status.CREATED).entity(uporabnik).build();

    }
    /*
    @Operation(description = "Posodobi uporabnika v bazi.", summary = "Posodabljanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Uporabnik je bil posodoboljen",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @PUT
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {

        uBean.posodobiUporabnika(id, uporabnik);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }
    /*
    @Operation(description = "Izbrise uprabnika iz baze.", summary = "Brisanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Uporabnik je bil izbirsan",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @DELETE
    public Response izbrisiUporabnika(@PathParam("id") Integer id) {

        uBean.odstraniUporabnika(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
