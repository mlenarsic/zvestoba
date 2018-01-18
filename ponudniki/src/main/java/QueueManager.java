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
        factory.setHost("192.168.99.100");
        //factory.setPort(15672);
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");
        // vzpostavitev povezave
        try{
            log.info("Conn: ");
            connection = factory.newConnection();
            log.info("Channel: ");
            channel = connection.createChannel();
            log.info("Queue declare: ");
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        } catch (Exception e) {
            log.info("Error: " + e);
        }
    }

    @PreDestroy
    private void stop() {

        // zaprtje channel-a in uničenje povezave
        try {
            channel.close();
            connection.close();
        } catch (Exception e){
            log.info("Close error:" + e);
        }

    }

    public void posljiObvestiloODodanemPonudniku(Integer ponudnikId) {
        // vstavljanje sporočila v vrstno
        String message = Integer.toString(ponudnikId);
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (Exception e){
            log.info("Publish error: " + e);
        }
    }
}