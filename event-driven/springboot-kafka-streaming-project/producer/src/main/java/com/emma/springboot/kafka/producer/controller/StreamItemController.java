package com.emma.springboot.kafka.producer.controller;

import com.emma.springboot.kafka.producer.dto.StreamItemVo;
import com.emma.springboot.kafka.producer.event.StreamItemEvent;
import com.emma.springboot.kafka.producer.service.KafkaProducer;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/producer")
public class StreamItemController {
    private static final Logger LOG = LoggerFactory.getLogger(StreamItemController.class);

    private KafkaProducer producer;

    public StreamItemController(KafkaProducer kafkaProducer) {
        this.producer = kafkaProducer;
    }

    @PostMapping("/item")
    public ResponseEntity<String> postItem(@RequestBody StreamItemVo streamItemVo) {
        StreamItemEvent streamItemEvent = new StreamItemEvent();
        if (Objects.isNull(streamItemVo) || Strings.isEmpty(streamItemVo.getKey())) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        streamItemEvent.setData(streamItemVo);
        streamItemEvent.setStatus("PENDING");
        streamItemEvent.setMessage("Send Stream Item Data");

        producer.sendMessage(streamItemEvent);
        return new ResponseEntity<>("Success", HttpStatusCode.valueOf(200));
    }

    @PostMapping("/items")
    public ResponseEntity<String> postItems(@RequestBody List<StreamItemVo> streamItemVoList) {

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
