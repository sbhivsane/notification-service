package com.learn.spring.notification_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.spring.notification_service.dto.SendEmailEventDTO;
import com.learn.spring.notification_service.utils.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;


@Service
public class SendEmailEventConsumer {


    private ObjectMapper objectMapper;

    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "sendEmail" ,groupId = "emailServiceConsumerGroup")
    public void handleSendEmailEvent(String message) throws JsonProcessingException {
        SendEmailEventDTO event = objectMapper.readValue(message, SendEmailEventDTO.class);
        String to = event.getToEmail();
        String body = event.getBody();
        String subject = event.getSubject();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sdbhivasane358@gmail.com", "myneaacmbmqnnhjc");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, to,subject, body);

    }
}
