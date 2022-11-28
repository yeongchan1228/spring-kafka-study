package springkafkastudy.kafkastudy.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import springkafkastudy.kafkastudy.model.Member;

@Component
public class Advanced2Producer {

    @Value("${spring.kafka.topic.test}")
    private String topic;
//    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Member> kafkaMemberTemplate;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Advanced2Producer(KafkaTemplate<String, Member> kafkaMemberTemplate) {
        this.kafkaMemberTemplate = kafkaMemberTemplate;
    }

//    public void asyncSend(String message) {
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
//        future.addCallback(new KafkaSendCallback<>() {
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                log.info("Success to send message.");
//            }
//
//            @Override
//            public void onFailure(KafkaProducerException ex) {
//                ProducerRecord<Object, Object> failedProducerRecord = ex.getFailedProducerRecord();
//                log.error("Fail to send message.", failedProducerRecord);
//            }
//        });
//    }

    public void asyncMemberSend(Member member) {
        ListenableFuture<SendResult<String, Member>> future = kafkaMemberTemplate.send("test-member", "member-id", member);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Member> result) {
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
