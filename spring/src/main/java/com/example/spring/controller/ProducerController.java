package com.example.spring.controller;

import com.example.spring.domain.MyMessage;
import com.example.spring.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/publish")
    public String publish(String message) {
        kafkaProducerService.send(message);
        return "published a messgae :" + message;
    }

    @RequestMapping("/publish2")
    public String publishWithCallback(String message) {
        kafkaProducerService.sendWithCallback(message);
        return "published a messgae with callback:" + message;
    }

    @RequestMapping("/publish3")
    public String publishWithCallback(MyMessage message) {
        kafkaProducerService.sendJson(message);
        return "published a messgae with callback:" + message.getName() + ", " + message.getMessage();
    }
}
