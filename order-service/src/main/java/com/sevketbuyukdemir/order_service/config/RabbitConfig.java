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
    public Queue emailQueue() {
        return new Queue("email-queue", true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("sms-queue", true);
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("payment-exchange", true, false);
    }

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange("notification-exchange", true, false);
    }

    @Bean
    public Binding bindPayment(@Qualifier("paymentQueue") Queue queue, @Qualifier("paymentExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("payment-service");
    }

    @Bean
    public Binding bindAllChannelsWithEmailNotification(@Qualifier("emailQueue") Queue queue, @Qualifier("notificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notify-via-all-channels");
    }

    @Bean
    public Binding bindEmailChannelWithEmailNotification(@Qualifier("emailQueue") Queue queue, @Qualifier("notificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notify-via-email-channel");
    }

    @Bean
    public Binding bindAllChannelsWithSMSNotification(@Qualifier("smsQueue") Queue queue, @Qualifier("notificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notify-via-all-channels");
    }

    @Bean
    public Binding bindSMSChannelWithEmailNotification(@Qualifier("smsQueue") Queue queue, @Qualifier("notificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notify-via-sms-channel");
    }

}
