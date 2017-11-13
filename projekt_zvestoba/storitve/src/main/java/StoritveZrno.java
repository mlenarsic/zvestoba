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
    public List<Storitev> pridobiStoritve() {
        Query q = em.createNamedQuery("Storitev.getAll");
        List rl = q.getResultList();
        List<Storitev> storitve = new LinkedList<Storitev>();
        for(int i = 0; i < rl.size(); i++) {
            storitve.add((Storitev) rl.get(i));
        }
        return storitve;
    }

    @BeleziKlice
    public Storitev pridobiStoritev(int sId) {
        Query q = em.createNamedQuery("Storitev.getById").setParameter("id",sId);
        List rl = q.getResultList();
        Storitev s = (Storitev) rl.get(0);
        return s;
    }

    @BeleziKlice
    @Transactional
    public void dodajStoritev(Storitev s) {
        em.persist(s);
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
        Storitev s = (Storitev) em.createNamedQuery("Storitev.getById").setParameter("id", sId).getResultList().get(0);
        em.remove(s);
    }
}
