import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class TockeZrno {

    @PersistenceContext(unitName = "zvestoba-jpa")
    private EntityManager em;

    private Logger log = Logger.getLogger(TockeZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializirano");
    }

    @BeleziKlice
    public List<Tocke> pridobiTocke() {
        Query q = em.createNamedQuery("Tocke.getAll");
        List rl = q.getResultList();
        if(rl.isEmpty()){
            return null;
        } else {
            List<Tocke> tocke = new LinkedList<Tocke>();
            for(int i = 0; i < rl.size(); i++){
                tocke.add((Tocke)rl.get(i));
            }
            return tocke;
        }
    }

    @BeleziKlice
    public Tocke pridobiTocko(int tockeId) {
        Query q = em.createNamedQuery("Tocke.getById").setParameter("id",tockeId);
        List rl = q.getResultList();
        if(rl.isEmpty()){
            return null;
        } else {
            Tocke t = (Tocke) rl.get(0);
            return t;
        }
    }

    @BeleziKlice
    public List<Tocke> pridobiTocke(Uporabnik u) {
        Query q = em.createNamedQuery("Tocke.getByUporabnik").setParameter("uporabnik",u);
        List rl = q.getResultList();
        if(rl.isEmpty()){
            return null;
        } else {
            List<Tocke> tockeU = new LinkedList<Tocke>();
            for (int i = 0; i < rl.size(); i++) {
                tockeU.add((Tocke) rl.get(i));
            }
            return tockeU;
        }
    }

    @BeleziKlice
    public Tocke pridobiTocko(Uporabnik u, int ponudnik_id) {
        Query q = em.createNamedQuery("Tocke.getByUporabnikAndPonudnikId").setParameter("pid",ponudnik_id)
                    .setParameter("u",u);
        List rl = q.getResultList();
        if(rl.isEmpty()){
            return null;
        } else {
            return (Tocke) rl.get(0);
        }
    }

    @BeleziKlice
    @Transactional
    public void dodajTocko(Tocke t) {
        em.persist(t);
    }

    @BeleziKlice
    @Transactional
    public void posodobiTocko(int tockeId, Tocke t){
        t.setId_kartice(tockeId);
        em.merge(t);
    }
    @BeleziKlice
    @Transactional
    public void odstraniTocko(int tockeId){
        Tocke t = (Tocke) em.createNamedQuery("Tocke.getById").setParameter("id", tockeId).getResultList().get(0);
        em.remove(t);
    }
}
