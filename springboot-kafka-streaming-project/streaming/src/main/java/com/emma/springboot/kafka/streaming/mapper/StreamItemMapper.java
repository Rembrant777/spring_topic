package com.emma.springboot.kafka.streaming.mapper;

import com.emma.springboot.kafka.streaming.entity.StreamItem;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

public class StreamItemMapper implements KeyValueMapper<String, StreamItem, KeyValue<String, StreamItem>> {
    @Override
    public KeyValue<String, StreamItem> apply(String s, StreamItem streamItem) {
        return new KeyValue<String, StreamItem>(streamItem.getKey(), streamItem);
    }
}
