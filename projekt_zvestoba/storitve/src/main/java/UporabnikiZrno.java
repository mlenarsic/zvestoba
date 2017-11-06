import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.*;
@ApplicationScoped
public class UporabnikiZrno {

    @PersistenceContext(unitName = "zvestoba-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {
        Query q = em.createNamedQuery("Uporabnik.getAll");
        List rl = q.getResultList();
        List<Uporabnik> uporabniki = new LinkedList<Uporabnik>();
        for(int i = 0; i < rl.size(); i++){
            Uporabnik u = (Uporabnik)rl.get(i);
            uporabniki.add((Uporabnik)rl.get(i));
            // System.out.println(u.getIme() + " " + u.getPriimek());
        }
        return uporabniki;
    }
}