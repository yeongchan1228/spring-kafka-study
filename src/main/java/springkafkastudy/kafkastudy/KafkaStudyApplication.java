package springkafkastudy.kafkastudy;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springkafkastudy.kafkastudy.producer.HelloProducer;

@SpringBootApplication
public class KafkaStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStudyApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(HelloProducer helloProducer) {
		return args -> {
			helloProducer.send();
		};
	}
}
