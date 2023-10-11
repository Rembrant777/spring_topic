package com.emma.kafka.consumer.database.repository;


import com.emma.kafka.consumer.database.entity.WikimediaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface WikimediaDataRespository extends JpaRepository<WikimediaData, Long> {
}
