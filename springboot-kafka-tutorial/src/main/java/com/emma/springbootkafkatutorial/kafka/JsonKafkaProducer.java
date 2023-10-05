package com.emma.springbootkafkatutorial.kafka;

import com.emma.springbootkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User usrMsg) {
        LOG.info("#sendMessage send message content {}", usrMsg);
        kafkaTemplate.send(topicJsonName, usrMsg);
    }
}
