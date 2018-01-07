import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class QueueManager {

    private Logger log = Logger.getLogger(QueueManager.class.getName());

    private Connection connection;
    private Channel channel;

    private final static String QUEUE_NAME = "send_id";

    @PostConstruct
    private void init() {

        ConnectionFactory factory = new ConnectionFactory();

        // konfiguracija factory-ja
        factory.setHost("localhost");
        // vzpostavitev povezave
        try{
            connection = factory.newConnection();
        } catch (Exception e) {
            log.info("Connection error");
        }

        // ustvarjanje channel-a
        try {
            channel = connection.createChannel();
        } catch (Exception e) {
            log.info("Channel error");
        }
        // deklaracija vrste na channel-u
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            log.info("Queue declare error");
        }
    }

    @PreDestroy
    private void stop() {

        // zaprtje channel-a in uničenje povezave
        try {
            channel.close();
            connection.close();
        } catch (Exception e){
            log.info("Close error");
        }

    }

    public void posljiObvestiloODodanemPonudniku(Integer ponudnikId) {
        // vstavljanje sporočila v vrstno
        String message = Integer.toString(ponudnikId);
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (Exception e){
            log.info("Publish error");
        }
    }
}