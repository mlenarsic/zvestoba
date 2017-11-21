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
    public List<Uporabnik> pridobiUporabnike() {
        Query q = em.createNamedQuery("Uporabnik.getAll");
        List rl = q.getResultList();
        if (rl.isEmpty()) {
            return null;
        } else {
            return (List<Uporabnik>) (rl);
        }
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
            em.persist(uporabnik);
        } catch (EntityExistsException e){
            e.printStackTrace();
        }
    }

    @BeleziKlice
    @Transactional
    public void posodobiUporabnika(int uporabnikId, Uporabnik uporabnik) {
        uporabnik.setId(uporabnikId);
        em.merge(uporabnik);
    }

    @BeleziKlice
    @Transactional
    public void odstraniUporabnika(int uporabnikId) {
        Uporabnik u = pridobiUporabnika(uporabnikId);
        if(u != null){
            em.remove(u);
        }
    }

}