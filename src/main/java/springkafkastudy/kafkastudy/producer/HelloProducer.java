package springkafkastudy.kafkastudy.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloProducer {

    @Value("${spring.kafka.topic.test}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public HelloProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send() {
        kafkaTemplate.send(topic, "Hello World!");
    }
}
