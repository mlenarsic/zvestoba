import javax.annotation.PostConstruct;
import javax.enterprise.context.*;
import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class IdentifikatorZrno {
    private int identifikator;


    private Random rand = new Random();
    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    public void init () {
        log.info("Inicializirano");
        identifikator = rand.nextInt(50) + 1;
    }

    public int getIdentifikator() {
        return identifikator;
    }
}
