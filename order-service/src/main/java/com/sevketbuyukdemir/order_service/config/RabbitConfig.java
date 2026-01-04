package com.sevketbuyukdemir.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue paymentQueue() {
        return new Queue("payment-queue", true);
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("payment-exchange", true, false);
    }

    @Bean
    public Binding bind(@Qualifier("paymentQueue") Queue queue, @Qualifier("paymentExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("payment-service");
    }

}
