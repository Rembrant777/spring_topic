package com.emma.springboot.kafka.producer.event;

import com.emma.springboot.kafka.producer.dto.StreamItemVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamItemEvent {
    private StreamItemVo data;
    private String message;
    private String status;

}
