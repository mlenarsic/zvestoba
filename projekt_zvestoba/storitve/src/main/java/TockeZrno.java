import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;
@ApplicationScoped
public class TockeZrno {

    @PersistenceContext(unitName = "zvestoba-jpa")
    private EntityManager em;

    @BeleziKlice
    public List<Tocke> pridobiTocke() {
        Query q = em.createNamedQuery("Tocke.getAll");
        List rl = q.getResultList();
        List<Tocke> tocke = new LinkedList<Tocke>();
        for(int i = 0; i < rl.size(); i++){
            tocke.add((Tocke)rl.get(i));
        }
        return tocke;
    }

    @BeleziKlice
    public Tocke pridobiTocko(int tockeId) {
        Query q = em.createNamedQuery("Tocke.getById").setParameter("id",tockeId);
        List rl = q.getResultList();
        Tocke t = (Tocke) rl.get(0);
        return t;
    }

    @BeleziKlice
    @Transactional
    public void dodajTocko(Tocke t) {
        em.persist(t);
    }

    @BeleziKlice
    @Transactional
    public void posodobiTocko(int tockeId, Tocke t){
        odstraniTocko(t.getId_kartice());
        t.setId_kartice(tockeId);
        dodajTocko(t);
    }
    @BeleziKlice
    @Transactional
    public void odstraniTocko(int tockeId){
        Tocke t = (Tocke) em.createNamedQuery("Tocke.getById").setParameter("id", tockeId).getResultList().get(0);
        em.remove(t);
    }
}
