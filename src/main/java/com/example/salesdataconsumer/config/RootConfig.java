package com.example.salesdataconsumer.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class RootConfig {

    public static final String GROUP_ID = "sales-101";
    @Bean
    public ConsumerFactory<String,String> consumerFactory()
    {
        Map<String,Object> props = new HashMap<>();

        String bootstrapServer = "pkc-6ojv2.us-west4.gcp.confluent.cloud:9092";
        String saslmechansim = "PLAIN";
        String jaasConfig = "org.apache.kafka.common.security.plain.PlainLoginModule required username='O2FP6ZCGYM5NVB4J' password='A9LK6mev3hEsKo/sj8zK6AdkSWnsRUs9fS8dAlkdcCtw8U0wyDqtJwQ2kgJyV71u';";
        String securityProtocol = "SASL_SSL";
        String sessionTimeout = "45000";

//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitValue);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,kafkaConsumerEnableAutoCommit);
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,kafkaSessionTimeOut);
//        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,heartbeatInterval);
//        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,heartbeatInterval);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,maxPollRecords);
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,maxPoolInterval);
//        if(activeProfile.equals("prod")) {
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String, String>(props,
                new StringDeserializer(),
                new StringDeserializer()
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return  factory;
    }



}
