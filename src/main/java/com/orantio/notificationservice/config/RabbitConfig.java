package com.orantio.notificationservice.config;

import com.orantio.notificationservice.constant.Constant;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitConfig {
    private final AmqpAdmin amqpAdmin;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    public void initialize() {
        amqpAdmin.declareQueue(new Queue(Constant.TOPIC_QUEUE, true));
        amqpAdmin.declareQueue(new Queue(Constant.NOTIFICATION_QUEUE, true));
    }
}
