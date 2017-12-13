import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.*;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class PonudnikiZrno {

    private EntityManager em;

    private Logger log = Logger.getLogger(PonudnikiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializirano");
    }

    public List<Ponudnik> pridobiPonudnike(QueryParameters query) {
        List<Ponudnik> ponudniki = JPAUtils.queryEntities(em, Ponudnik.class, query);
        return ponudniki;
    }

    public Ponudnik pridobiPonudnika(int ponudnikId) {
        try {
            return em.getReference(Ponudnik.class, ponudnikId);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}