package com.example.sb.rabbit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitConfig {

    private String topic;
//    private String queue;

//    @Bean
//    Queue queue() {
//        return new Queue(queue, false);
//    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topic);
    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }

}
