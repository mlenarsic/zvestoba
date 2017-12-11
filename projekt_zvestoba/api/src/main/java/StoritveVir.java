
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

@Path("storitve")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StoritveVir {

    @Inject
    private StoritveZrno sBean;

    @Context
    protected UriInfo uriInfo;

    /*
    @Operation(description = "Vrne seznam storitev iz baze.", summary = "Seznam storitev", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam storitev",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @GET
    public Response vrniStoritve(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Storitev> storitve = sBean.pridobiStoritve(query);
        return Response.status(Response.Status.OK).entity(storitve).build();

    }
    /*
    @Operation(description = "Vrne storitev z dolocenim id-jem iz baze.", summary = "Storitev  id-jem", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @GET
    public Response vrniStoritev(@PathParam("id") Integer id) {

        Storitev storitev = sBean.pridobiStoritev(id);
        return Response.status(Response.Status.OK).entity(storitev).build();

    }
    /*
    @Operation(description = "Doda storitev v bazo.", summary = "Dodajanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila dodana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @POST
    public Response dodajStoritev(Storitev storitev) {

        sBean.dodajStoritev(storitev);
        return Response.status(Response.Status.CREATED).entity(storitev).build();

    }
    /*
    @Operation(description = "Posodobi storitev z dolocenim id-jem v bazi.", summary = "Posodabljanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila posodobljena",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @PUT
    public Response posodobiStoritev(@PathParam("id") Integer id, Storitev storitev) {

        sBean.posodobiStoritev(id, storitev);
        return Response.status(Response.Status.OK).entity(storitev).build();

    }
    /*
    @Operation(description = "Izbrise storitev z dolocenim id-jem iz baze.", summary = "Brisanje storitve", tags = "storitve", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Storitev je bila izbrisana",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @DELETE
    public Response izbrisiStoritev(@PathParam("id") Integer id) {

        sBean.odstraniStoritev(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
