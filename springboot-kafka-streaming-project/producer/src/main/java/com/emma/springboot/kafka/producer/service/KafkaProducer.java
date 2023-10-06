package com.emma.springboot.kafka.producer.service;

import com.emma.springboot.kafka.producer.event.StreamItemEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, StreamItemEvent> kafkaTemplate;

    public KafkaProducer(NewTopic topic, KafkaTemplate<String, StreamItemEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(StreamItemEvent event) {
        LOG.info("#sendMessage Order Event => {}", event.toString());
        Message<StreamItemEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, this.topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
