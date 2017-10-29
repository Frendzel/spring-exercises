package pl.erbel;


import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static Logger LOG = Logger.getLogger(MessageListener.class);

    @JmsListener(destination = "messageBox")
    public void readMessage(Message message) {
        LOG.info("We have it: " + message);
    }

    @JmsListener(destination = "deadChannel")
    public void readMessage(String message) {
        LOG.info("We have it: " + message);
    }

    @JmsListener(destination = "messageBox")
    public void readMessage2(Message message) {
        LOG.info("We have it2: " + message);
    }
    @JmsListener(destination = "messageBox")
    public void readMessage3(Message message) {
        LOG.info("We have it3: " + message);
    }

    @JmsListener(destination = "messageBox")
    public void readMessage4(Message message) {
        LOG.info("We have it4: " + message);
    }
}
