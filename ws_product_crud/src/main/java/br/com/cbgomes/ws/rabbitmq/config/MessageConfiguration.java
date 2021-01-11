package br.com.cbgomes.ws.rabbitmq.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MessageConfiguration {

    @Value("${ws.product.rabbitmq.exchange}")
    private String exchange;


    @Bean
    public Exchange declareExchange(){
        return ExchangeBuilder.directExchange(this.exchange).durable(true).build();
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
