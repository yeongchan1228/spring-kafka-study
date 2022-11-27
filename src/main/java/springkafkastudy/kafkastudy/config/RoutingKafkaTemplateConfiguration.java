package springkafkastudy.kafkastudy.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class RoutingKafkaTemplateConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public RoutingKafkaTemplate routingKafkaTemplate() {
        return new RoutingKafkaTemplate(factories());
    }

    private Map<Pattern, ProducerFactory<Object, Object>> factories() {
        Map<Pattern, ProducerFactory<Object, Object>> factories = new LinkedHashMap<>();
        factories.put(Pattern.compile(".*"), defaultProducerFactory());
        return factories;

    }

    private ProducerFactory<Object, Object> defaultProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProps());

    }

    private Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        // 해당 값은 Default 값과 유사하다.
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

}
