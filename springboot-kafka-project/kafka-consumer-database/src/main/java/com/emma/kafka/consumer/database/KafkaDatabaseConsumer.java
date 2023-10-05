package com.emma.kafka.consumer.database;

import com.emma.kafka.consumer.database.repository.WikimediaDataRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRespository dataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRespository dataRepository) {
        this.dataRepository = dataRepository;
    }
}
