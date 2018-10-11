package classes;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(description = "Ustrezni kartici dolocenega uporabnika doda tocke za opravljeno storitev.", summary = "Dodajanje tock", tags = "upravljanje zvestobe", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocke so bile dodane",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Tocke.class))
            )})
    @Path("{id}")
    @POST
    @CrossOrigin
    public Response opravljenaStoritev(@PathParam("id") Integer id, Storitev storitev) {

        uzBean.dodajTockeZaOpravljenoStoritev(storitev);
        return Response.status(Response.Status.OK).build();

    }

    @Path("{id}")
    @PUT
    @CrossOrigin
    public Response koristenjeTock(@PathParam("id") Integer id, Integer pid) {
        uzBean.koristiTocke(id, pid);
        return Response.status(Response.Status.OK).build();
    }

}
