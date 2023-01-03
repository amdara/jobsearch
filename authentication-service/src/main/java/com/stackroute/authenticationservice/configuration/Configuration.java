package com.stackroute.authenticationservice.configuration;


import com.stackroute.authenticationservice.constant.Constant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@org.springframework.context.annotation.Configuration

public class Configuration {


    //Create queue
    @Bean
    public Queue queue() {
        return new Queue(Constant.PRODUCER_AUTHENTICATION_RECRUITER_QUEUE);
    }

    //Create exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constant.PRODUCER_AUTHENTICATION_RECRUITER_EXCHANGE);
    }

    //Bind together (queue and exchange).
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(Constant.PRODUCER_AUTHENTICATION_RECRUITER_ROUTING_KEY);
    }

    //We are passing entire class so that we have to convert as json.so we use messageconverter to send particular queue
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //create Rabbit MQ template .connectionfactory as input paramter and we are going to create rabbit mq template
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template=new RabbitTemplate(connectionFactory);

        template.setMessageConverter(messageConverter());

        return template;
    }


    //JOB_SEEKER queue
    @Bean
    public Queue queue1() {
        return new Queue(Constant.PRODUCER_AUTHENTICATION_JOBSEEKER_QUEUE);
    }

    //Create exchange
    @Bean
    public TopicExchange exchange1() {
        return new TopicExchange(Constant.PRODUCER_AUTHENTICATION_JOBSEEKER_EXCHANGE);
    }

    //Bind together (queue and exchange).
    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange1) {
        return BindingBuilder
                .bind(queue1)
                .to(exchange1)
                .with(Constant.PRODUCER_AUTHENTICATION_JOBSEEKER_ROUTING_KEY);
    }

    //We are passing entire class so that we have to convert as json.so we use messageconverter to send particular queue
    @Bean
    public MessageConverter messageConverter1() {
        return new Jackson2JsonMessageConverter();
    }

    //create Rabbit MQ template .connectionfactory as input paramter and we are going to create rabbit mq template
    public AmqpTemplate template1(ConnectionFactory connectionFactory1) {
        RabbitTemplate template=new RabbitTemplate(connectionFactory1);

        template.setMessageConverter(messageConverter());

        return template;
    }
    //SIGNUP EMAIL queue
    @Bean
    public Queue queue2() {
        return new Queue(Constant.PRODUCER_AUTHENTICATION_SIGNUPEMAIL_QUEUE);
    }

    //Create exchange
    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(Constant.PRODUCER_AUTHENTICATION_SIGNUPEMAIL_EXCHANGE);
    }

    //Bind together (queue and exchange).
    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange2) {
        return BindingBuilder
                .bind(queue2)
                .to(exchange2)
                .with(Constant.PRODUCER_AUTHENTICATION_SIGNUPEMAIL_ROUNTING_KEY);
    }

    //We are passing entire class so that we have to convert as json.so we use messageconverter to send particular queue
    @Bean
    public MessageConverter messageConverter2() {
        return new Jackson2JsonMessageConverter();
    }

    //create Rabbit MQ template .connectionfactory as input paramter and we are going to create rabbit mq template
    public AmqpTemplate template2(ConnectionFactory connectionFactory2) {
        RabbitTemplate template=new RabbitTemplate(connectionFactory2);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
