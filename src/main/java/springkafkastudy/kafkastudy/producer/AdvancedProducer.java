package springkafkastudy.kafkastudy.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class AdvancedProducer {

    @Value("${spring.kafka.topic.test}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RoutingKafkaTemplate routingKafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AdvancedProducer(KafkaTemplate<String, String> kafkaTemplate, RoutingKafkaTemplate routingKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.routingKafkaTemplate = routingKafkaTemplate;
    }

    public void syncSend(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        try {
            log.info("Success to send message.");
            SendResult<String, String> result = future.get(5, TimeUnit.SECONDS);
            log.info("success result {}", result.getProducerRecord());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void asyncSend(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Success to send message.");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Object, Object> failedProducerRecord = ex.getFailedProducerRecord();
                log.error("Fail to send message.", failedProducerRecord);
            }
        });
    }
}
