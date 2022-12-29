package com.example.spring.controller;

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
}
