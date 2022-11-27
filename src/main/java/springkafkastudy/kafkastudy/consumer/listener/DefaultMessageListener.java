package springkafkastudy.kafkastudy.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class DefaultMessageListener implements MessageListener<String, String> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("[DefaultMessageListener] data = {}", data.value());
    }
}
