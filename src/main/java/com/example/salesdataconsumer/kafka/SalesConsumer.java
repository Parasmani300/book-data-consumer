package com.example.salesdataconsumer.kafka;

import com.example.salesdataconsumer.model.SalesHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class SalesConsumer {
    @KafkaListener(topics = "BOOK-STORE-SAMPLE",groupId = "sales-101")
    public void consumeMessage(Object message) throws IOException {
        ConsumerRecord<String,String> consumerRecord = (ConsumerRecord<String, String>) message;
        postHttpRequestWithJSON(consumerRecord.value());
        System.out.println(consumerRecord.value());
    }

    public void postHttpRequestWithJSON(String jsonRecord) throws IOException {
        String jsonRecord1 = "";
        if(jsonRecord != null && !jsonRecord.startsWith("[")){
            jsonRecord1 = "[" + jsonRecord + "]";
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8983/solr/sample_core/update");
        StringEntity entity = new StringEntity(jsonRecord1);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        System.out.println("Published the message");
        client.close();
    }

}
