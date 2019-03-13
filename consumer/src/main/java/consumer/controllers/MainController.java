package consumer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @KafkaListener(topics = "test")
    public void consume(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Payload String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message + " " + partition));
    }
}
