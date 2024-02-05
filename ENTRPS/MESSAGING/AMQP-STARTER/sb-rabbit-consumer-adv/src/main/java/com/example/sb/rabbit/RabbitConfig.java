package com.example.sb.rabbit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitConfig {


    private String queue;
    private String topic;

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    public Declarables bindings() {

        List<Binding> bindings = new ArrayList<>();
        TopicExchange topicExchange = new TopicExchange(topic, true, false);
        bindings.add(BindingBuilder.bind(new Queue(queue)).to(topicExchange).with("foo.bar.baz"));
        return new Declarables(bindings);
    }

}
