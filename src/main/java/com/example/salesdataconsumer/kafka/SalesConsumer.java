package com.example.salesdataconsumer.kafka;

import com.example.salesdataconsumer.model.SalesHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SalesConsumer {
    @KafkaListener(topics = "sales-publisher",groupId = "sales-101")
    public void consumeMessage(Object message) throws JsonProcessingException {
        ConsumerRecord<String,Object> consumerRecord = (ConsumerRecord<String, Object>) message;
        ObjectMapper mapper = new ObjectMapper();
        SalesHistory salesHistory =  mapper.readValue((String) consumerRecord.value(), SalesHistory.class);
        System.out.println(salesHistory);
    }
}
