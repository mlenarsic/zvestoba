package classes;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
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
import java.util.logging.Logger;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    private Client httpClient;
    private Pretvorbe p;

    @Inject
    private UporabnikiZrno uBean;

    @Inject
    private TockeZrno tBean;

    @Context
    protected UriInfo uriInfo;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    private void init(){
        p = new Pretvorbe();
        httpClient = ClientBuilder.newClient();
    }
    @Operation(description = "Vrne seznam uporabnikov", summary = "Seznam uporabnikov", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Uporabnik.class)),
                    headers = {@Header(name = "",
                            schema = @Schema(type = "Uporabniki"))}
            )})
    @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uBean.pridobiUporabnike(query);
        return Response.status(Response.Status.OK).entity(uporabniki).build();
    }

    @Operation(description = "Vrne uporabnika z id-jem.", summary = "klasi.Uporabnik", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Uporabnik",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Uporabnik.class))
            )})
    @Path("{id}")
    @GET
    public Response vrniUporabnika(@PathParam("id") Integer id) {


        Uporabnik uporabnik = uBean.pridobiUporabnika(id);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }

    @Operation(description = "Doda uporabnika v bazo.", summary = "Dodajanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Uporabnik je bil dodan",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Uporabnik.class))
            )})
    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {
        System.out.println("\n");
        System.out.println(uporabnik.tostring());
        uBean.dodajUporabnika(uporabnik);
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uBean.pridobiUporabnike(query);
        System.out.println("po dodajanju!");
        for(int i = 0; i < uporabniki.size();i++){
            System.out.println(uporabniki.get(i).tostring());
        }
        System.out.println("\n");
        return Response.status(Response.Status.CREATED).entity(uporabnik).build();

    }

    @Operation(description = "Posodobi uporabnika v bazi.", summary = "Posodabljanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Uporabnik je bil posodoboljen",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Uporabnik.class))
            )})
    @Path("{id}")
    @PUT
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {

        uBean.posodobiUporabnika(id, uporabnik);
        return Response.status(Response.Status.OK).entity(uporabnik).build();

    }

    @Operation(description = "Izbrise uprabnika iz baze.", summary = "Brisanje uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Uporabnik je bil izbirsan",
                    content = @Content(
                            schema = @Schema(implementation
                                    = Uporabnik.class))
            )})
    @Path("{id}")
    @DELETE
    public Response izbrisiUporabnika(@PathParam("id") Integer id) {

        uBean.odstraniUporabnika(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }


    @Operation(description = "Vrne tocke dolocenega uporabnika.", summary = "Tocke uporabnika", tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Tocke uporabnika",
                    content = @Content(
                            schema = @Schema(implementation
                                    = UporabnikTocke.class))
            )})
    @Path("{id}/tocke")
    @GET
    public Response pridobiTockeUporabnika(@PathParam("id") Integer id) {
        Uporabnik u = uBean.pridobiUporabnika(id);
        UporabnikTocke U = new UporabnikTocke();
        List<Tocke> t = tBean.pridobiTocke(u);
        if(t == null){
           U.setTocke(null);
        } else {
            List<Tocke_ponudnik> tp = vrniTockeSPonudniki(t);
            U.setTocke(tp);
        }
        U.setId(u.getId());
        U.setIme(u.getIme());
        U.setPriimek(u.getPriimek());
        U.setEmail(u.getEmail());
        U.setUporabnisko_ime(u.getUporabnisko_ime());
        return Response.status(Response.Status.OK).entity(U).build();

    }

    public Tocke_ponudnik vrniTockoSPonudnikom(Tocke t){
        Ponudnik p =  httpClient
                .target("http://ponudniki.herokuapp.com/v1/ponudniki/" + t.getPonudnik_id())
                .request()
                .get(new GenericType<Ponudnik>(){});
        Tocke_ponudnik TP = new Tocke_ponudnik();
        TP.setId_kartice(t.getId_kartice());
        TP.setZbrane_tocke(t.getZbrane_tocke());
        TP.setUporabnik(null);
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

}
