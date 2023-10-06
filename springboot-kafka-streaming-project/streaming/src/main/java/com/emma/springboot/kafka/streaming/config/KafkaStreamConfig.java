package com.emma.springboot.kafka.streaming.config;


import com.emma.springboot.kafka.streaming.entity.StreamItem;
import com.emma.springboot.kafka.streaming.mapper.StreamItemMapper;
import com.emma.springboot.kafka.streaming.reducer.StreamItemReducer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${kafka.stream.topic}")
    private String kafkaStreamTopic;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, new JsonSerde<>(StreamItem.class));
        props.put(JsonDeserializer.KEY_DEFAULT_TYPE, String.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, StreamItem.class);
        return new StreamsConfig(props);
    }

    @Bean
    public KStream<String, StreamItem> kStreamJson(StreamsBuilder builder) {
        KStream<String, StreamItem> stream = builder.stream( kafkaStreamTopic,
                Consumed.with(Serdes.String(), new JsonSerde<>(StreamItem.class)));
        KTable<String, StreamItem> combinedDocuments = stream
                .map(new StreamItemMapper())
                .groupByKey()
                .reduce(new StreamItemReducer(), Materialized.<String, StreamItem, KeyValueStore<Bytes, byte[]>>
                        as("streams-json-store"));
        combinedDocuments.toStream().to("stream-json-input", Produced.with(Serdes.String(),
                new JsonSerde<>(StreamItem.class)));

        return stream;
    }
}
