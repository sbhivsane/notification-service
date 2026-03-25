package com.learn.spring.notification_service.controller;

import com.learn.spring.notification_service.dto.SendEmailEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        kafkaTemplate.send("sendEmail", message);
    }

}
