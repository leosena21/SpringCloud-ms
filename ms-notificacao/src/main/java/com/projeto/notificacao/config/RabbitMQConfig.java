package com.projeto.notificacao.config;

import lombok.Setter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.queue")
public class RabbitMQConfig {

    @Setter
    private String emailQueue;

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
