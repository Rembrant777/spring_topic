package com.emma.event.emailservice.kafka;

import com.emma.event.basedomains.dto.OrderEvent;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Actually the OrderConsumer which consume
 * the message from the specific topic and the
 * given specific groupId it means the current
 * instance of the consumer belongs to the unique group that
 * is named by the groupId(spring.kafka.consumer.group-id: stock)
 */
@Service
public class OrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOG.info("#consume recv event non-null status {}", Objects.nonNull(orderEvent));

        if (!Strings.isBlank(orderEvent.getOrder().getOrderId())) {
            LOG.info("#consume recv order event ont the email service domain{}", orderEvent.toString());
        }
    }
}
