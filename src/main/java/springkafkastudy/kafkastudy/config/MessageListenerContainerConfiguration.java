package springkafkastudy.kafkastudy.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import springkafkastudy.kafkastudy.consumer.listener.DefaultMessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageListenerContainerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaMessageListenerContainer messageListenerContainer() {
        ContainerProperties containerProps = new ContainerProperties("test-event");
        containerProps.setGroupId("test-event-container");
        containerProps.setAckMode(ContainerProperties.AckMode.BATCH);
        containerProps.setMessageListener(new DefaultMessageListener());
        
        KafkaMessageListenerContainer container = new KafkaMessageListenerContainer(containerFactory(), containerProps);
        container.setAutoStartup(false); // 자동으로 시작하는 것이 아닌, 내가 원할 때 실행
        
        return container;
    }

    private ConsumerFactory containerFactory() {
        return new DefaultKafkaConsumerFactory(props());
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
