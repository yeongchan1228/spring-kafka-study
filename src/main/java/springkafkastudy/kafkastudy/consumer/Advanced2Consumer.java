package springkafkastudy.kafkastudy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Advanced2Consumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(id = "test-event-id", topics = "${spring.kafka.topic.test}")
    public void listen(String message) {
        log.info("[Consumer] message = {}", message);
    }
}
