package com.emma.springbootkafkatutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    // createTopic this method will be invoekd automatically
    // because it is annotated by @Bean this annotation
    // and what's more the function's return value will be
    // assigned to the NewTopic which will be accessed in the global context
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic createSupportJsonTopic() {
        return TopicBuilder.name(topicJsonName)
                .build();
    }



}
