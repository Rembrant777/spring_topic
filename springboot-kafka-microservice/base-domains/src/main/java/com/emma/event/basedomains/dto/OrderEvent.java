package com.emma.event.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The OrderEvent is the class that we gonna to
 * send via the message brokers.
 * And to transport the data between consumer and the producer of the Apache Kafka.
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String message;
    private String status;
    private Order order;
}
