package com.nixx._001.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nixx
 */
@RestController
public class Producer {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public void sendMessage() {
        kafkaTemplate.send("topic-01", "Hello Kafka!");

    }

}
