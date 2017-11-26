import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class StoritveZrno {

    @PersistenceContext(unitName = "zvestoba-jpa")
    private EntityManager em;

    private Logger log = Logger.getLogger(StoritveZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializirano");
    }

    @BeleziKlice
    public List<Storitev> pridobiStoritve(QueryParameters query) {
        List<Storitev> storitve = JPAUtils.queryEntities(em, Storitev.class, query);
        return storitve;
    }

    @BeleziKlice
    public Storitev pridobiStoritev(int sId) {
        try {
            return em.getReference(Storitev.class, sId);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @BeleziKlice
    @Transactional
    public void dodajStoritev(Storitev s) {
        try {
            em.persist(s);
        } catch (EntityExistsException e){
            e.printStackTrace();
        }
    }

    @BeleziKlice
    @Transactional
    public void posodobiStoritev(int sId, Storitev s){
        s.setStoritevId(sId);
        em.merge(s);
    }
    @BeleziKlice
    @Transactional
    public void odstraniStoritev(int sId){
        Storitev s = pridobiStoritev(sId);
        if(s != null){
            em.remove(s);
        }

    }
}
