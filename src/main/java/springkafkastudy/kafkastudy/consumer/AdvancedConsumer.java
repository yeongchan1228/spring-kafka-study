package springkafkastudy.kafkastudy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
public class AdvancedConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @SendTo
    @KafkaListener(id = "test-request-id", topics = "test-request")
    public String listenRequest(String message) {
        log.info("[Consumer] message = {}", message);
        return "reply test";
    }

}
