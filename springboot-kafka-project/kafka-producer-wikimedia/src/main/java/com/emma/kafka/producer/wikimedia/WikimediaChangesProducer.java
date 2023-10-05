package com.emma.kafka.producer.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class WikimediaChangesProducer {
    private static final Logger LOG = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        // to read real time stream data from wikimedia, we use the event source

        // here we create an event handler to build connection to kafka
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // here we define the upstream source url address and this time in our demo
        // we take the wikimedia as our dataset's source
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

        // after passing the config options to it we invoke the build
        EventSource eventSource = builder.build();

        // star event source to subscribe data from
        eventSource.start();
    }
}