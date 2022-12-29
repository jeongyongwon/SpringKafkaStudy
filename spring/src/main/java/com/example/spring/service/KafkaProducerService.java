package com.example.spring.service;

import com.example.spring.domain.MyMessage;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic5";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, MyMessage> newKafkaTemplate;

    public void  sendJson(MyMessage message) {
        newKafkaTemplate.send(TOPIC_NAME, message);
    }

    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed ==>  " + message + " due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent " + message + "offset:" + result.getRecordMetadata().offset());
            }
        });
    }
}
