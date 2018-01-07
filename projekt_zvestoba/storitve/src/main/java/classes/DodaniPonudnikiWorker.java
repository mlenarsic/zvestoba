package classes;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.logging.Logger;

@ApplicationScoped
public class DodaniPonudnikiWorker {

    private Logger log = Logger.getLogger(DodaniPonudnikiWorker.class.getName());

    private Connection connection;
    private Channel channel;

    private final static String QUEUE_NAME = "send_id";

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        ConfigurationUtil configurationUtil = ConfigurationUtil.getInstance();

        ConnectionFactory factory = new ConnectionFactory();

        // konfiguracija factory-ja
        factory.setHost("localhost");

        // dodajanje Consumer-ja na channel
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                log.info(" [x] Received '" + message + "'");
            }
        };
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

        // dodajanje poslušalca na channel
        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (Exception e) {
            log.info("Dodajanje poslušalca exception");
        }

    }

    private void stop(@Observes @Destroyed(ApplicationScoped.class) Object destroyed) {

        // zaprtje channel-a in uničenje povezave
        try {
            channel.close();
            connection.close();
        } catch (Exception e){
            log.info("Close error");
        }

    }
}