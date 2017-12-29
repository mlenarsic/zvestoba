package classes;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.LinkedList;
import java.util.List;

public class Pretvorbe {
    private Client httpClient;
    public Pretvorbe(){}

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public Tocke_ponudnik vrniTockoSPonudnikom(Tocke t){
        System.out.println("---------OVDEEEEE----------");
        Ponudnik p =  httpClient
                .target("http://ponudniki.herokuapp.com/v1/ponudniki/" + t.getPonudnik_id())
                .request()
                .get(new GenericType<Ponudnik>(){});
        System.out.println("---------NEXT STEP----------");
        Tocke_ponudnik TP = new Tocke_ponudnik();
        TP.setId_kartice(t.getId_kartice());
        TP.setZbrane_tocke(t.getZbrane_tocke());
        TP.setUporabnik(t.getUporabnik());
        TP.setPonudnik(p);
        return TP;


    }
    public List<Tocke_ponudnik> vrniTockeSPonudniki(List<Tocke> tocke){
        List<Tocke_ponudnik> tp = new LinkedList<>();
        System.out.println(tocke.size() + " TOCKE SIZE!!!");
        for(int i = 0; i < tocke.size(); i++){
            System.out.println(tocke.get(i).tostring());
            tp.add(vrniTockoSPonudnikom(tocke.get(i)));
        }
        return tp;
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

}
