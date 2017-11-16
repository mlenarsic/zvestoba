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
            List<Uporabnik> uporabniki = new LinkedList<Uporabnik>();
            for (int i = 0; i < rl.size(); i++) {
                uporabniki.add((Uporabnik) rl.get(i));
            }
            return uporabniki;
        }
    }

    @BeleziKlice
    public Uporabnik pridobiUporabnika(int uporabnikId) {
        Query q = em.createNamedQuery("Uporabnik.getById").setParameter("id", uporabnikId);
        List rl = q.getResultList();
        if (rl.isEmpty()) {
            return null;
        } else {
            return (Uporabnik) rl.get(0);
        }
    }
    @BeleziKlice
    @Transactional
    public void dodajUporabnika(Uporabnik uporabnik) {
        em.persist(uporabnik);
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
        Uporabnik u = (Uporabnik) em.createNamedQuery("Uporabnik.getById").setParameter("id", uporabnikId).getResultList().get(0);
        em.remove(u);
    }

}