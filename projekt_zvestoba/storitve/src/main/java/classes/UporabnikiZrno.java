package classes;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.*;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikiZrno {

    @PersistenceContext(unitName = "zvestoba-jpa")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializirano");
    }

    @BeleziKlice
    public List<Uporabnik> pridobiUporabnike(QueryParameters query) {
        List<Uporabnik> uporabniki = JPAUtils.queryEntities(em, Uporabnik.class, query);
        return uporabniki;
    }

    @BeleziKlice
    public Uporabnik pridobiUporabnika(int uporabnikId) {
        try {
            return em.getReference(Uporabnik.class, uporabnikId);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    @BeleziKlice
    @Transactional
    public void dodajUporabnika(Uporabnik uporabnik) {
        try {
            System.out.println("V dodajanju uporabnika v zrnu!" + uporabnik.tostring());
            em.getTransaction().begin();
            em.persist(uporabnik);
            em.getTransaction().commit();
        } catch (PersistenceException p){
            p.printStackTrace();
        }
    }

    @BeleziKlice
    @Transactional
    public void posodobiUporabnika(int uporabnikId, Uporabnik uporabnik) {
        uporabnik.setId(uporabnikId);
        em.getTransaction().begin();
        em.merge(uporabnik);
        em.getTransaction().commit();
    }

    @BeleziKlice
    @Transactional
    public void odstraniUporabnika(int uporabnikId) {
        Uporabnik u = pridobiUporabnika(uporabnikId);
        if(u != null){
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        }
    }

}