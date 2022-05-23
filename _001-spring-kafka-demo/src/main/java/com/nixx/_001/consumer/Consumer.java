package com.nixx._001.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author nixx
 */

@Component
public class Consumer {
    @KafkaListener(topics = {"topic-01"}, groupId = "group1")
    public void consumerMessage(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord.value());
    }
}
