package springkafkastudy.kafkastudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import springkafkastudy.kafkastudy.producer.Advanced2Producer;

@SpringBootApplication
public class KafkaStudyApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(KafkaStudyApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(Advanced2Producer advanced2Producer,
									KafkaMessageListenerContainer kafkaMessageListenerContainer) {
		return args -> {
			kafkaMessageListenerContainer.start();

			advanced2Producer.asyncSend("test-message5.");
		};
	}

//	@Bean
//	public ApplicationRunner runner(AdvancedProducer advancedProducer) {
//		return args -> {
//			advancedProducer.asyncSend("test message1.");
//			advancedProducer.syncSend("test message2.");
//			advancedProducer.routingSend("test message3.");
//			advancedProducer.replyingSend("test-request", "test-message4.");
//		};
//	}

//	@Bean
//	public ApplicationRunner runner(AdminClient adminClient) {
//		return args -> {
//			Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
//			for (String topicName : topics.keySet()) {
//				log.info("topicName = {},     topicListing = {}", topicName, topics.get(topicName));
//
//				Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).allTopicNames().get();
//				log.info("topicName = {},     description = {}", topicName, description.get(topicName));
//
//				if (!topics.get(topicName).isInternal()) {
//					adminClient.deleteTopics(Collections.singleton(topicName));
//				}
//			}
//
//		};
//	}

}
