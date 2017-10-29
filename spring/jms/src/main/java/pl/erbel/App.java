package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class App implements CommandLineRunner {

    @Autowired
    JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.OBJECT);
        converter.setTypeIdPropertyName("type");
        return converter;
    }

    @Override
    public void run(String... strings) throws Exception {
        Message message = new Message();
        message.setId(1);
        message.setContent("Hello World");
        message.setTopic("ASAP");
        for (int i = 0; i < 10; i++) {
            jmsTemplate.convertAndSend("messageBox", message);
            jmsTemplate.convertAndSend("deadChannel", message);
            Thread.sleep(500);
        }
    }
}
