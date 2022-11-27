package springkafkastudy.kafkastudy;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springkafkastudy.kafkastudy.producer.AdvancedProducer;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class KafkaStudyApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(KafkaStudyApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(AdminClient adminClient) {
		return args -> {
			Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
			for (String topicName : topics.keySet()) {
				log.info("topicName = {},     topicListing = {}", topicName, topics.get(topicName));

				Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).allTopicNames().get();
				log.info("topicName = {},     description = {}", topicName, description.get(topicName));

				if (!topics.get(topicName).isInternal()) {
					adminClient.deleteTopics(Collections.singleton(topicName));
				}
			}

		};
	}

}
