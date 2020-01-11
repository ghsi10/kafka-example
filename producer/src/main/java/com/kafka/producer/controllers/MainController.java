package com.kafka.producer.controllers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final String TOPIC = "test";
    private static final int NUM_PARTITIONS = 2;
    private static final short REPLICATION = 1;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MainController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(TOPIC, NUM_PARTITIONS, REPLICATION);
    }

    @GetMapping("/{message}")
    public void sendMessage(@PathVariable String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        kafkaTemplate.send(TOPIC, message);
    }
}
