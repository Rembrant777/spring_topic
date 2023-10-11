package com.emma.springboot.kafka.streaming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class StreamItem {
    private String key;
    private List<String> values;

    public StreamItem() {
     this.values = new ArrayList<>();
    }
}
