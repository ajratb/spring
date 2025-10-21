package study.sboot.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * From Spring for Apache Kafka / Introduction / Quick Tour
 * (<a href="https://docs.spring.io/spring-kafka/reference/quick-tour.html">Quick Tour</a>)
 *
 */

@EnableScheduling
@SpringBootApplication
public class SbootKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootKafkaApplication.class, args);
    }


    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) {
        System.out.println(in);
    }

    @Autowired KafkaTemplate<String, String> template;
    @Scheduled(fixedDelay = 3000)
    public void send(){
//        System.out.println("test");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        template.send("topic1", "test" + LocalDateTime.now().format(formatter));
    }
}
