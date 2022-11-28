package springkafkastudy.kafkastudy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springkafkastudy.kafkastudy.model.Member;

import javax.validation.Valid;

@Component
public class Advanced2Consumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(id = "test-member-id", topics = "test-member", containerFactory = "kafkaJsonContainerFactory")
    public void listenMember(@Valid Member member) {
        log.info("[Consumer] member = {}", member.toString());
    }

//    @KafkaListener(id = "test-event-id", topics = "${spring.kafka.topic.test}")
//    public void listen(String message) {
//        log.info("[Consumer] message = {}", message);
//    }

//    @KafkaListener(id = "test-event-id", topics = "${spring.kafka.topic.test}", concurrency = "2", clientIdPrefix = "test-id")
//    public void listen(String message,
//                       ConsumerRecordMetadata metadata) {
//        log.info("[Consumer] message = {}", message);
//        log.info("[Consumer] offset = {}", metadata.offset());
//    }
//
//    @KafkaListener(id = "test-event-id", topics = "${spring.kafka.topic.test}", concurrency = "2", clientIdPrefix = "test-id")
//    public void listen(String message,
//                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
//                       @Header(KafkaHeaders.REPLY_PARTITION) int partition,
//                       @Header(KafkaHeaders.OFFSET) long offset) {
//        log.info("[Consumer] message = {}", message);
//        log.info("[Consumer] timestamp = {}", timestamp);
//        log.info("[Consumer] partition = {}", partition);
//        log.info("[Consumer] offset = {}", offset);
//    }
}
