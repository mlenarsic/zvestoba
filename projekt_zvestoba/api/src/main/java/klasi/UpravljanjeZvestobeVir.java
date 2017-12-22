package klasi;//import io.swagger.oas.annotations.Operation;
//import io.swagger.oas.annotations.headers.Header;
//import io.swagger.oas.annotations.media.Content;
//import io.swagger.oas.annotations.media.Schema;
//import io.swagger.oas.annotations.responses.ApiResponse;
//import io.swagger.oas.annotations.security.SecurityRequirement;
//import org.eclipse.jetty.http2.api.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("upravljanjeZvestobe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UpravljanjeZvestobeVir {
    @Inject
    private UpravljanjeZvestobeZrno uzBean;
    /*
    @Operation(description = "Ustrezni kartici dolocenega uporabnika doda tocke za opravljeno storitev.", summary = "Dodajanje tock", tags = "upravljanje zvestobe", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Tocke so bile dodane",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    @Path("{id}")
    @POST
    public Response opravljenaStoritev(@PathParam("id") Integer id, Storitev storitev) {

        uzBean.dodajTockeZaOpravljenoStoritev(storitev);
        return Response.status(Response.Status.OK).build();

    }
    /*
    @Operation(description = "Ustrezni kartici dolocenega uporabnika odsteje stevilo tock.", summary = "Koristenje tock", tags = "upravljanje zvestobe", responses = {
            @ApiResponse(responseCode = "200",
                    description = "klasi.Tocke so bile uporabljene",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Session.class))
            )})
    */
    /*
    @Path("{id}")
    @PUT
    public Response koristenjeTock(@PathParam("id") Integer id, Integer pid, Integer tocke) {
        uzBean.koristiTocke((int)id, (int)pid,(int)tocke);
        return Response.status(Response.Status.OK).build();
    }
    */

}
