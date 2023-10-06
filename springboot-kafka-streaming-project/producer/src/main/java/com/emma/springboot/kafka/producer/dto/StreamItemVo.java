package com.emma.springboot.kafka.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class StreamItemVo {
    private String key;
    private List<String> values;

    public StreamItemVo() {
        this.values = new ArrayList<>();
    }
}


